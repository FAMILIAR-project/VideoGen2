import java.util.ArrayList;

public class variante {
	
	ArrayList<String> listVideo = new ArrayList<String>();
	ArrayList<Long> listSizeVideo = new ArrayList<Long>();
	ArrayList<Long> listdureeVideo = new ArrayList<Long>();
	
	Long TotalSize =0L;
	Long Totalduree =0L;
	
	
	variante(){
	}
	
	public variante AddVideo(String Name,Long Size) {
		listVideo.add(Name);
		listSizeVideo.add(Size);
		TotalSize+=Size;
		return this;
	}
	
	public float GetPoidMO() {
		return TotalSize/1048576f;
	}
	
	public variante clone() {
		variante v = new variante();
		v.listVideo = (ArrayList<String>) listVideo.clone();
		v.listSizeVideo = (ArrayList<Long>) listSizeVideo.clone();
		v.listdureeVideo = (ArrayList<Long>) listdureeVideo.clone();
		v.TotalSize = TotalSize;
		v.Totalduree = Totalduree;
		return v;
	}
}
