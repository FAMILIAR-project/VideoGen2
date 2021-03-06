# Generator
Our main program is the class Main.java, we have been able to implement the requested functionalities, the program itself contains some examples of commands as comments. Example:

```Java
case "GENERATE_CSV_FILE": // example of input: GENERATE_CSV_FILE example2
```

# Organization
- The generatedVideos folder as its name suggests contains the videos generated whether they are intermediate or final
- The videos folder contains the source videos used for the tests
- The textFiles folder contains the generated playLists files (txt), CSV files, html pages
- The transformation package contains the different classes allowing the different transformations
- The util package contains the necessary functionalities for the transformations (ex: filters)
- Main.java class is our main program
- The Tests.xtend class for testing

# Use
The Main.java class allows us to execute our generator, its main() function uses a 'Scanner(System.in)' and 'switch case' to process the different commands.
A demo of the program is in the parent folder (videoGenProject)

# Commands

## Validation

To validate a '.videogen' file, go through the main or directly by calling validator (videoGen) from the Utils class

## Clean

It is important to delete (or move) the generated files before restarting a new generation prosses, so the cleanTestFiles() function of the class Utils does the cleaning

## Text playlist file generation

The playlists are generated by the function generate(videoGen) of the class PlayList.java

## Concatination of playlist videos

The concatination is done with the concat() function of the class Utils, there is also concat_and_play () which launches the resulting video too (require vlc)

## Export to Gif

To export a video to gif, there is the function videoToGif () of the class VideoToGif.xtend, it is also possible to create for a specification, a gif per sequence, with the function modelToGifs () of the same class


## Generate the longest playList
For this, we use the transformation toLongestPlayList () of the class LongestVariant.java

## Generate html page with thumbnails
The toHtml() transformation of the Html.java class allows to assemble the html page

## Generation of CSV
For the analysis of the sizes of the set of possible variants for a model, the function getAllVariants () of the class Etude.java allows to create the file CSV containing the necessary information
