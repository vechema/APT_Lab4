package mockEasy;

/**
 * Excerpted from the book, "Pragmatic Unit Testing"
 * ISBN 0-9745140-1-2
 * Copyright 2003 The Pragmatic Programmers, LLC.  All Rights Reserved.
 * Visit www.PragmaticProgrammer.com
 */

import junit.framework.*;
import java.util.ArrayList;
import static org.easymock.EasyMock.*;

public class TestMp3Player extends TestCase {

	protected Mp3Player mp3;
	protected Mp3Player mp3Mock;
	protected ArrayList list = new ArrayList();

	public void setUp() {
		// By hand mock set up
		mp3 = new MockMp3Player();

		list = new ArrayList();
		list.add("Bill Chase -- Open Up Wide");
		list.add("Jethro Tull -- Locomotive Breath");
		list.add("The Boomtown Rats -- Monday");
		list.add("Carl Orff -- O Fortuna");
		
		//EasyMock set up
		mp3Mock = createMock(Mp3Player.class);
	}

	public void testPlay() {

		mp3.loadSongs(list);
		assertFalse(mp3.isPlaying());
		mp3.play();
		assertTrue(mp3.isPlaying());
		assertTrue(mp3.currentPosition() != 0.0);
		mp3.pause();
		assertTrue(mp3.currentPosition() != 0.0);
		mp3.stop();
		assertEquals(mp3.currentPosition(), 0.0, 0.1);

	}

	public void testPlayNoList() {

		// Don't set the list up
		assertFalse(mp3.isPlaying());
		mp3.play();
		assertFalse(mp3.isPlaying());
		assertEquals(mp3.currentPosition(), 0.0, 0.1);
		mp3.pause();
		assertEquals(mp3.currentPosition(), 0.0, 0.1);
		assertFalse(mp3.isPlaying());
		mp3.stop();
		assertEquals(mp3.currentPosition(), 0.0, 0.1);
		assertFalse(mp3.isPlaying());
	}

	public void testAdvance() {

		mp3.loadSongs(list);

		mp3.play();

		assertTrue(mp3.isPlaying());

		mp3.prev();
		assertEquals(mp3.currentSong(), list.get(0));
		assertTrue(mp3.isPlaying());

		mp3.next();
		assertEquals(mp3.currentSong(), list.get(1));
		mp3.next();
		assertEquals(mp3.currentSong(), list.get(2));
		mp3.prev();

		assertEquals(mp3.currentSong(), list.get(1));
		mp3.next();
		assertEquals(mp3.currentSong(), list.get(2));
		mp3.next();
		assertEquals(mp3.currentSong(), list.get(3));
		mp3.next();
		assertEquals(mp3.currentSong(), list.get(3));
		assertTrue(mp3.isPlaying());
	}

	public void testPlayMock() {
		
		//Setting up mock
		mp3Mock.loadSongs(list);
		expect(mp3Mock.isPlaying()).andReturn(false);
		mp3Mock.play();
		expect(mp3Mock.isPlaying()).andReturn(true);
		expect(mp3Mock.currentPosition()).andReturn(1.0);
		mp3Mock.pause();
		expect(mp3Mock.currentPosition()).andReturn(1.0);
		mp3Mock.stop();
		expect(mp3Mock.currentPosition()).andReturn(0.0);
		replay(mp3Mock);

		//Using mock to test
		mp3Mock.loadSongs(list);
		assertFalse(mp3Mock.isPlaying());
		mp3Mock.play();
		assertTrue(mp3Mock.isPlaying());
		assertTrue(mp3Mock.currentPosition() != 0.0);
		mp3Mock.pause();
		assertTrue(mp3Mock.currentPosition() != 0.0);
		mp3Mock.stop();
		assertEquals(mp3Mock.currentPosition(), 0.0, 0.1);

	}

	public void testPlayNoListMock() {
		
		//Setting up mock
		expect(mp3Mock.isPlaying()).andReturn(false);
		mp3Mock.play();
		expect(mp3Mock.isPlaying()).andReturn(false);
		expect(mp3Mock.currentPosition()).andReturn(0.0);
		mp3Mock.pause();
		expect(mp3Mock.currentPosition()).andReturn(0.0);
		expect(mp3Mock.isPlaying()).andReturn(false);
		mp3Mock.stop();
		expect(mp3Mock.currentPosition()).andReturn(0.0);
		expect(mp3Mock.isPlaying()).andReturn(false);
		replay(mp3Mock);

		//Using mock
		// Don't set the list up
		assertFalse(mp3Mock.isPlaying());
		mp3Mock.play();
		assertFalse(mp3Mock.isPlaying());
		assertEquals(mp3Mock.currentPosition(), 0.0, 0.1);
		mp3Mock.pause();
		assertEquals(mp3Mock.currentPosition(), 0.0, 0.1);
		assertFalse(mp3Mock.isPlaying());
		mp3Mock.stop();
		assertEquals(mp3Mock.currentPosition(), 0.0, 0.1);
		assertFalse(mp3Mock.isPlaying());
	}

	public void testAdvanceMock() {
		
		//Setting up mock
		mp3Mock.loadSongs(list);
		
		mp3Mock.play();
		expect(mp3Mock.isPlaying()).andReturn(true);
		
		mp3Mock.prev();
		expect(mp3Mock.currentSong()).andReturn((String) list.get(0));
		expect(mp3Mock.isPlaying()).andReturn(true);
		
		mp3Mock.next();
		expect(mp3Mock.currentSong()).andReturn((String) list.get(1));
		mp3Mock.next();
		expect(mp3Mock.currentSong()).andReturn((String) list.get(2));
		mp3Mock.prev();
		
		expect(mp3Mock.currentSong()).andReturn((String) list.get(1));
		mp3Mock.next();
		expect(mp3Mock.currentSong()).andReturn((String) list.get(2));
		mp3Mock.next();
		expect(mp3Mock.currentSong()).andReturn((String) list.get(3));
		mp3Mock.next();
		expect(mp3Mock.currentSong()).andReturn((String) list.get(3));
		expect(mp3Mock.isPlaying()).andReturn(true);
		replay(mp3Mock);
		
		//Testing with mock
		mp3Mock.loadSongs(list);

		mp3Mock.play();
		assertTrue(mp3Mock.isPlaying());

		mp3Mock.prev();
		assertEquals(mp3Mock.currentSong(), list.get(0));
		assertTrue(mp3Mock.isPlaying());

		mp3Mock.next();
		assertEquals(mp3Mock.currentSong(), list.get(1));
		mp3Mock.next();
		assertEquals(mp3Mock.currentSong(), list.get(2));
		mp3Mock.prev();

		assertEquals(mp3Mock.currentSong(), list.get(1));
		mp3Mock.next();
		assertEquals(mp3Mock.currentSong(), list.get(2));
		mp3Mock.next();
		assertEquals(mp3Mock.currentSong(), list.get(3));
		mp3Mock.next();
		assertEquals(mp3Mock.currentSong(), list.get(3));
		assertTrue(mp3Mock.isPlaying());
	}

}
