
import java.util.ArrayList;

public class variety {
	
	ArrayList<String> listVideo = new ArrayList<String>();
	ArrayList<Long> listSizeVideo = new ArrayList<Long>();
	ArrayList<Long> listVideotimes = new ArrayList<Long>();	

	Long TotalSize =0L;	
	
	variety(){
	}
	
	public variety AddVideo(String Name,Long Size) {
		listVideo.add(Name);
		listSizeVideo.add(Size);
		TotalSize+=Size;
		return this;
	}
	
	public float GetPoidMO() {
		return TotalSize/1048576f;
	}
	
	public variety clone() {
		variety v = new variety();
		v.listVideo = (ArrayList<String>) listVideo.clone();
		v.listSizeVideo = (ArrayList<Long>) listSizeVideo.clone();
		v.TotalSize = TotalSize;
		return v;
	}
}
