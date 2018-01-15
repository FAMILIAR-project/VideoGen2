import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.xtext.example.mydsl.videoGen.VideoDescription;

public class CSVFileWriter {

	private final String COMMA_DELIMITER = ",";

	private final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	private ArrayList<Long> sizes = new ArrayList<Long>();
	private ArrayList<Long> lenghts = new ArrayList<Long>();

	private String FILE_HEADER;

	public void writeCsvFile(ArrayList<VideoDescription> videos, ArrayList<ArrayList<VideoDescription>> playlists) {
		File file;
		String head = "ID,";
		int i = -1;
		long size = 0;
		long lenght = 0;

		// write the line in the csv
		for (VideoDescription video : videos) {
			if (video.getVideoid() != null) {
				head += video.getVideoid() + ",";
			} else {
				head += "video foud in :" + videos.indexOf(video) + ",";
			}

		}
		head += "SIZE,LENGHT";
		FILE_HEADER = head;
		FileWriter fileWriter = null;

		try {

			fileWriter = new FileWriter("bref.CSV");

			// Write the CSV file header

			fileWriter.append(FILE_HEADER.toString());

			// Add a new line separator after the header

			fileWriter.append(NEW_LINE_SEPARATOR);
			for (ArrayList<VideoDescription> playlist : playlists) {
				i++;
				fileWriter.append(i + COMMA_DELIMITER);

				for (VideoDescription video : videos) {

					if (playlist.contains(video)) {
						fileWriter.append("TRUE");
						file = new File(video.getLocation());
						size += file.length();
						lenght += Util.getFileDuration(video.getLocation());

					} else {
						fileWriter.append("FALSE");
					}
					fileWriter.append(COMMA_DELIMITER);

				}

				sizes.add(size);
				lenghts.add(lenght);
				fileWriter.append(Long.toString(size));

				fileWriter.append(COMMA_DELIMITER);
				
				fileWriter.append(Long.toString(lenght));

				fileWriter.append(NEW_LINE_SEPARATOR);

				
				size=0;
				lenght=0;

			}

			// Write a new student object list to the CSV file

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {

			System.out.println("Error in CsvFileWriter !!!");

			e.printStackTrace();

		} finally {

			try {

				fileWriter.flush();

				fileWriter.close();

			} catch (IOException e) {

				System.out.println("Error while flushing/closing fileWriter !!!");

				e.printStackTrace();

			}

		}

	}

	public ArrayList<Long> getSizes() {
		return sizes;
	}

	public void setSizes(ArrayList<Long> sizes) {
		this.sizes = sizes;
	}

}