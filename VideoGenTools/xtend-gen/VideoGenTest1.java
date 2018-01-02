import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.junit.Test;

@SuppressWarnings("all")
public class VideoGenTest1 {
  @Test
  public void testLoadModel() {
    final ArrayList<int[]> res = new ArrayList<int[]>();
    final ArrayList<String> videos = new ArrayList<String>();
    videos.add("jaunatan.mp4");
    videos.add("sheep.mp4");
    videos.add("wave.mp4");
    videos.add("stan.mp4");
    for (final String video : videos) {
      res.add(VideoGenUtils.getResolution(video));
    }
    int output_width = 0;
    int output_height = 0;
    for (final int[] r : res) {
      {
        int _get = r[0];
        boolean _greaterThan = (_get > output_width);
        if (_greaterThan) {
          output_width = r[0];
        }
        int _get_1 = r[1];
        boolean _greaterThan_1 = (_get_1 > output_height);
        if (_greaterThan_1) {
          output_height = r[1];
        }
      }
    }
    int i = 0;
    final ArrayList<String> playlist = new ArrayList<String>();
    for (final String video_1 : videos) {
      {
        int _plusPlus = i++;
        final int[] r_1 = res.get(_plusPlus);
        playlist.add(VideoGenUtils.resize(video_1, r_1[0], r_1[1], output_width, output_height));
      }
    }
    VideoGenUtils.genPlaylist(((String[])Conversions.unwrapArray(playlist, String.class)), "playlist.mp4");
  }
}
