angular.module('kdarcel.vlc-player', ['kdarcel.vlc-player.tpl', 'ui.bootstrap'])
.constant('VERSION', 'v1.2.0')
.run(["$rootScope", "VERSION", function ($rootScope, VERSION) {
    $rootScope.version = VERSION;
}])
.filter('range', function() {
    return function(input, total) {
        total = parseInt(total);
        for (var i = 0; i < total; i++)
            input.push(i);
        return input;
    };
})
.filter('time2String', function() {
    return function duration(duration) {
        if (!duration)
            return '00:00';

        var seconds = parseInt((duration / 1000) % 60);
        var minutes = parseInt((duration / (1000 * 60)) % 60);
        var hours   = parseInt((duration / (1000 * 60 * 60)) % 24);

        var durationString = '';

        if (hours) durationString += ((hours < 10) ? '0' + hours : hours) + ':';
        durationString += ((minutes < 10) ? '0' + minutes : minutes) + ':';
        durationString += (seconds < 10) ? '0' + seconds : seconds;

        return durationString;
    };
})
.factory('poollingFactory', ["$timeout", function ($timeout) {
    var timeIntervalInSec = 1;

    function callFnOnInterval(fn, timeInterval) {
        var promise = $timeout(fn, 1000 * timeIntervalInSec);

        return promise.then(function(){
            callFnOnInterval(fn, timeInterval);
        });
    }

    return {
        callFnOnInterval: callFnOnInterval
    };
}])
.directive('vlcplayer', ["$templateCache", "poollingFactory", function ($templateCache, poollingFactory) {
    return {
        restrict: 'E',
        replace: true,
        template: $templateCache.get('angular-vlc.tpl.html'),
        link: function (scope, element, attributes) {
            var setupVlcPlayer = function(vlcData) {
                if (vlcData.url && vlcData.filename) {
                    scope.vlc = document.getElementById('vlc');
                    if (scope.vlc)
                    {
                        if (scope.vlc.playlist.items.count > 0)
                            scope.vlc.playlist.items.clear();

                        var options = [':vout-filter=deinterlace', ':deinterlace-mode=linear'];
                        var id = scope.vlc.playlist.add(vlcData.url, vlcData.filename, options);

                        if (vlcData.autoplay == 'true')
                            scope.vlc.playlist.playItem(id);

                        scope.vlc.version = scope.vlc.versionInfo();
                        scope.vlc.toolbarWidth = {'width': '640'};
                        scope.vlc.toolbarClass = 'toolbar-vlc';
                        scope.vlc.fullscreenClass = 'vlc-window';
                    }
                }
            };

            scope.$watch(function () {
                if (scope.vlc) {
                    // if the file is playing
                    if (vlc.input.state == 3) {
                        scope.videoDuration = scope.vlc.input.length;
                        scope.vlc.openning = false;
                        scope.vlc.buffer = false;
                    }

                    // if there is an error
                    if (vlc.input.state == 7 && scope.vlc.error === null)
                        scope.vlc.error = true;

                    // player is openning or is paused or is buffering or is stopping or is ended
                    if (vlc.input.state == 4 || vlc.input.state == 5 || vlc.input.state == 6) {
                        if (vlc.input.state == 2)
                            scope.vlc.buffer = true;
                        if (vlc.input.state == 1)
                            scope.vlc.openning = true;
                        scope.vlc.toolbar = true;
                    }
                }

                return {
                    'url': attributes.vlcUrl,
                    'filename': attributes.vlcFilename,
                    'autoplay': attributes.vlcAutoplay
                };
            }, setupVlcPlayer, true);

            scope.vlcKeyEvent = function(event) {
                if (event.keyCode == 32) // Keypress 'space'
                    scope.vlc.playlist.togglePause();
            };

            scope.vlcToolbarActive = function(isHover) {
                scope.vlc.toolbar = isHover;
            };

            scope.vlcTogglePause = function() {
                scope.vlc.playlist.togglePause();
            };

            scope.vlcToggleMute = function() {
                scope.vlc.audio.toggleMute();
            };

            scope.vlcSwitchAudioTrack = function(trackNumber) {
                scope.vlc.audio.track = trackNumber;
            };

            scope.vlcSwitchSubtitleTrack = function(trackNumber) {
                scope.vlc.subtitle.track = trackNumber;
            };

            scope.changeTime = function(event) {
                var outside = document.getElementById('player-progress');
                var inside = document.getElementById('progress');
                var pct = Math.floor((event.offsetX / outside.offsetWidth) * 100);
                vlc.input.time = (scope.videoDuration * pct) / 100;
            };

            $(document).on('webkitfullscreenchange mozfullscreenchange fullscreenchange', function(e){
                var pos = scope.vlc.input.position;
                scope.vlc.playlist.stop();

                if ((!document.fullscreenElement && !document.mozFullScreenElement && !document.webkitFullscreenElement)) {
                    scope.vlc.embedFullscreen = { 'width': '640', 'height': '360'};
                    scope.vlc.toolbarWidth = {'width': '640'};
                    scope.vlc.toolbarClass = 'toolbar-vlc';
                    scope.vlc.fullscreenClass = 'vlc-window';

                } else {
                    scope.vlc.embedFullscreen = {'width': screen.width, 'height': screen.height};
                    scope.vlc.toolbarWidth = {'width': screen.width};
                    scope.vlc.toolbarClass = 'toolbar-vlc-fullscreen';
                    scope.vlc.fullscreenClass = 'vlc-fullscreen';
                }

                scope.vlc.playlist.play();
                scope.vlc.input.position = pos;
            });

            scope.vlcToggleFullscreen = function() {

                if (!document.fullscreenElement && !document.mozFullScreenElement && !document.webkitFullscreenElement) {

                    var elem = document.getElementById('player');
                    if (elem.requestFullscreen)
                      elem.requestFullscreen();
                    else if (elem.msRequestFullscreen)
                      elem.msRequestFullscreen();
                    else if (elem.mozRequestFullScreen)
                      elem.mozRequestFullScreen();
                    else if (elem.webkitRequestFullscreen)
                      elem.webkitRequestFullscreen();
                } else {

                    if (document.cancelFullScreen) {
                      document.cancelFullScreen();
                    } else if (document.mozCancelFullScreen) {
                      document.mozCancelFullScreen();
                    } else if (document.webkitCancelFullScreen) {
                      document.webkitCancelFullScreen();
                    }
                }
            };

            poollingFactory.callFnOnInterval(function () {
                if (scope.vlc) {
                    scope.videoCurrentTime = scope.vlc.input.time;
                    scope.vlc.timer = ( scope.videoCurrentTime / scope.videoDuration ) * 100;
                }
            });
        }
    };
}]);

(function(module) {
try {
  module = angular.module('kdarcel.vlc-player.tpl');
} catch (e) {
  module = angular.module('kdarcel.vlc-player.tpl', []);
}
module.run(['$templateCache', function($templateCache) {
  $templateCache.put('angular-vlc.tpl.html',
    '<div class="player-container" id="player" ng-keydown="vlcKeyEvent($event);">\n' +
    '    <div ng-class="{true: \'player-blur-vlc\' }[vlc.buffer == true]" class="{{ vlc.fullscreenClass }}">\n' +
    '        <object classid="clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921" events="true" width="100%" height="100%" tabindex="0">\n' +
    '            <embed pluginspage="http://www.videolan.org"\n' +
    '                   type="application/x-vlc-plugin"\n' +
    '                   version="VideoLAN.VLCPlugin.2"\n' +
    '                   allowfullscreen="true"\n' +
    '                   width="640"\n' +
    '                   height="360"\n' +
    '                   toolbar="false"\n' +
    '                   branding="false"\n' +
    '                   windowless="true"\n' +
    '                   id="vlc"\n' +
    '                   ng-style="vlc.embedFullscreen">\n' +
    '        </object>\n' +
    '    </div>\n' +
    '    <div class="video-controls" ng-mouseover="vlcToolbarActive(true);" ng-mouseleave="vlcToolbarActive(false);">\n' +
    '        <div class="{{ vlc.toolbarClass }}" ng-class="{true: \'toolbar-active-vlc\', false: \'toolbar-disabled-vlc\'}[vlc.error == true || vlc.toolbar == true]" ng-style="vlc.toolbarWidth">\n' +
    '            <div class="progress-vlc" id="player-progress" ng-click="changeTime($event)">\n' +
    '                <div class="progress-bar" id="progress" role="progressbar" aria-valuenow="{{ vlc.timer }}" aria-valuemin="0" aria-valuemax="100" ng-style="{width : ( vlc.timer + \'%\' ) }">\n' +
    '                </div>\n' +
    '            </div>\n' +
    '            <div class="form-inline pull-left">\n' +
    '                <button type="button" class="btn btn-default btn-default-vlc btn-xs" tooltip="Play/Pause" ng-click="vlcTogglePause()">\n' +
    '                    <span class="glyphicon" ng-class="vlc.playlist.isPlaying ? \'glyphicon-pause\' : \'glyphicon-play\'"></span>\n' +
    '                </button>\n' +
    '                <span class="vlc-text-white">{{ videoCurrentTime | time2String }} <span ng-if="videoDuration">/ {{ videoDuration | time2String }}</span></span>\n' +
    '            </div>\n' +
    '            <div class="form-inline pull-right">\n' +
    '                <button type="button" class="btn btn-default btn-default-vlc btn-xs" tooltip="Mute/Unmute" ng-click="vlcToggleMute()">\n' +
    '                    <span class="glyphicon" ng-class="vlc.audio.mute ? \'glyphicon-volume-off\' : \'glyphicon-volume-up\'"></span>\n' +
    '                </button>\n' +
    '                <div class="btn-group display-hover dropup" ng-disabled="vlc.audio.count == 0" uib-dropdown>\n' +
    '                    <button type="button" class="btn btn-default btn-default-vlc btn-xs" uib-dropdown-toggle data-toggle="dropdown" ng-disabled="vlc.audio.count == 0">\n' +
    '                        <span class="glyphicon glyphicon-sound-5-1"></span>\n' +
    '                    </button>\n' +
    '                    <ul class="dropdown-menu" role="menu">\n' +
    '                        <li ng-repeat="n in [] | range:vlc.audio.count">\n' +
    '                            <a href="" ng-click="vlcSwitchAudioTrack(n)">\n' +
    '                                {{ vlc.audio.description(n) }}&nbsp;<span class="glyphicon glyphicon-ok" ng-show="vlc.audio.track == n"></span>\n' +
    '                            </a>\n' +
    '                        </li>\n' +
    '                    </ul>\n' +
    '                </div>\n' +
    '                <div class="btn-group display-hover dropup" ng-disabled="vlc.subtitle.count == 0" uib-dropdown>\n' +
    '                    <button type="button" class="btn btn-default  btn-default-vlc btn-xs" uib-dropdown-toggle data-toggle="dropdown" ng-disabled="vlc.subtitle.count == 0">\n' +
    '                        <span class="glyphicon glyphicon-subtitles"></span>\n' +
    '                    </button>\n' +
    '                    <ul class="dropdown-menu" role="menu">\n' +
    '                        <li ng-repeat="n in [] | range:vlc.subtitle.count">\n' +
    '                            <a href="" ng-click="vlcSwitchSubtitleTrack(n)">\n' +
    '                                {{ vlc.subtitle.description(n) }}&nbsp;<span class="glyphicon glyphicon-ok" ng-show="vlc.subtitle.track == n"></span>\n' +
    '                            </a>\n' +
    '                        </li>\n' +
    '                    </ul>\n' +
    '                </div>\n' +
    '                <div class="btn-group dropup" uib-dropdown>\n' +
    '                    <button type="button" class="btn btn-default btn-default-vlc btn-xs" uib-dropdown-toggle tooltip="Parameters">\n' +
    '                        <span class="glyphicon glyphicon-cog"></span>\n' +
    '                    </button>\n' +
    '                    <ul class="dropdown-menu" uib-dropdown-menu>\n' +
    '                        <li><a href="https://github.com/Tuxity/angular-vlc/tree/{{ version }}" target="blank"> About angular-vlc </a></li>\n' +
    '                        <li><a href=""> VLC {{ vlc.version }}</a></li>\n' +
    '                    </ul>\n' +
    '                </div>\n' +
    '                <button type="button" class="btn btn-default btn-default-vlc btn-xs" tooltip="Fullscreen" ng-click="vlcToggleFullscreen()">\n' +
    '                    <span class="glyphicon glyphicon-resize-full"></span>\n' +
    '                </button>\n' +
    '            </div>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="error-vlc" ng-if="vlc.error">\n' +
    '        <p>There is an error with the link your given...</p>\n' +
    '    </div>\n' +
    '    <div class="error-vlc" ng-if="vlc.buffer">\n' +
    '        <p>Video is actually buffering, please wait...</p>\n' +
    '    </div>\n' +
    '    <div class="error-vlc" ng-if="vlc.openning">\n' +
    '        <p>Video will be open in few seconds...</p>\n' +
    '    </div>\n' +
    '</div>\n' +
    '');
}]);
})();
