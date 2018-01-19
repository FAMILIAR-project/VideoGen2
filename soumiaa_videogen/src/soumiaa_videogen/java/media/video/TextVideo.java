package soumiaa_videogen.java.media.video;

public class TextVideo {

	private String content;
	private TextPosition position;
	private Integer textSize;
	private String color;
	public TextVideo(String content, TextPosition position, Integer textSize, String color) {
		super();
		this.content = content;
		this.position = position;
		this.textSize = textSize;
		this.color = color;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public TextPosition getPosition() {
		return position;
	}
	public void setPosition(TextPosition position) {
		this.position = position;
	}
	public Integer getTextSize() {
		return textSize;
	}
	public void setTextSize(Integer textSize) {
		this.textSize = textSize;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
	
}
