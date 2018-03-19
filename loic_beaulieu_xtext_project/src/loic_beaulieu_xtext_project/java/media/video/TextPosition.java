package loic_beaulieu_xtext_project.java.media.video;

public enum TextPosition {
	TOP,MID,BOT;
	public static TextPosition stringToPosition(String val) {
		if (val == null || val.equals("")) {
			return BOT;
		}
		String modif = val.toLowerCase();
		if (val.equals("top")) {
			return TOP;
		}
		if (val.equals("mid")||val.equals("middle")||val.equals("center")) {
			return MID;
		}
		if (val.equals("bot") || val.equals("bottom")) {
			return BOT;
		}
		return null;
	}
}
