package test

import generator.Generator
import generator.VideoGenHelper
import java.util.ArrayList

import static org.junit.Assert.*

class Test {
	
	var files = new ArrayList<String>;
	var badfiles = new ArrayList<String>;
	
	@org.junit.Test
	public def void testInterpretation(){
		files.add("videogenTestFile/example1.videogen");
		files.add("videogenTestFile/example2.videogen");
		files.add("videogenTestFile/example3.videogen");
		
		badfiles.add("videogenTestFile/example4Bad.videogen");
		badfiles.add("videogenTestFile/example5Bad.videogen");
		badfiles.add("videogenTestFile/example6Bad.videogen");
		badfiles.add("videogenTestFile/example7Bad.videogen");
		badfiles.add("videogenTestFile/example8Bad.videogen");
		badfiles.add("videogenTestFile/example9Bad.videogen");

		for(String file : files){
			val video = new VideoGenHelper().loadVideoGenerator(file);
			var generetor = new Generator();
			
			assertFalse(generetor.hasError(video));
		}
		
		for(String file : badfiles){
			val video = new VideoGenHelper().loadVideoGenerator(file);
			var generetor = new Generator();
			
			assertTrue(generetor.hasError(video));
		}
		
	}
	
	@org.junit.Test
	public def void testFilter(){
		val video = new VideoGenHelper().loadVideoGenerator("videogenTestFile/example4.videogen");
		var generetor = new Generator();
		assertFalse(generetor.hasError(video));
		generetor.generate(video,"/tmp/example4.mp4");
	}
	
	@org.junit.Test
	public def void testText(){
		val video = new VideoGenHelper().loadVideoGenerator("videogenTestFile/example5.videogen");
		var generetor = new Generator();
		assertFalse(generetor.hasError(video));
		generetor.generate(video,"/tmp/example5.mp4");
	}
	
	@org.junit.Test
	public def void testAllSimple(){
		val video = new VideoGenHelper().loadVideoGenerator("videogenTestFile/example6.videogen");
		var generetor = new Generator();
		assertFalse(generetor.hasError(video));
		generetor.generate(video,"/tmp/example6.mp4");
	}
	
	@org.junit.Test
	public def void testAllComplex(){
		val video = new VideoGenHelper().loadVideoGenerator("videogenTestFile/example7.videogen");
		var generetor = new Generator();
		assertFalse(generetor.hasError(video));
		generetor.generate(video,"/tmp/example7.mp4");
	}
	
	@org.junit.Test
	public def void testSimpleGif(){
		val video = new VideoGenHelper().loadVideoGenerator("videogenTestFile/example8.videogen");
		var generetor = new Generator();
		assertFalse(generetor.hasError(video));
		generetor.generateGif(video,"/tmp/example8.gif");
	}
	
	@org.junit.Test
	public def void testComplexGif(){
		val video = new VideoGenHelper().loadVideoGenerator("videogenTestFile/example9.videogen");
		var generetor = new Generator();
		assertFalse(generetor.hasError(video));
		generetor.generateGif(video,"/tmp/example9.gif");
	}
	
	@org.junit.Test
	public def void testVignette(){
		val video = new VideoGenHelper().loadVideoGenerator("videogenTestFile/example1.videogen");
		var generetor = new Generator();
		assertFalse(generetor.hasError(video));
		generetor.generateVignette(video)
	}
	
}
