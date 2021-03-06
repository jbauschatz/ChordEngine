package com.conchordance.music;

import java.util.HashMap;
import static com.conchordance.music.NoteName.*;

public class Scale {
	
	private static final HashMap<Note, Scale> MAJOR_SCALES;

	static {
		MAJOR_SCALES = new HashMap<>();
		MAJOR_SCALES.put(new Note(C, 0), new Scale(C, new int[]{0,0,0,0,0,0,0}));
		MAJOR_SCALES.put(new Note(C, 1), new Scale(C, new int[]{1,1,1,1,1,1,1}));
		MAJOR_SCALES.put(new Note(D, -1), new Scale(D, new int[]{-1,-1,0,-1,-1,-1,0}));
		MAJOR_SCALES.put(new Note(D, 0), new Scale(D, new int[]{0,0,1,0,0,0,1}));
		MAJOR_SCALES.put(new Note(D, 1), new Scale(D, new int[]{1,1,2,1,1,1,2}));
		MAJOR_SCALES.put(new Note(E, -1), new Scale(E, new int[]{-1,0,0,-1,-1,0,0}));
		MAJOR_SCALES.put(new Note(E, 0), new Scale(E, new int[]{0,1,1,0,0,1,1}));
		MAJOR_SCALES.put(new Note(F, 0), new Scale(F, new int[]{0,0,0,-1,0,0,0}));
		MAJOR_SCALES.put(new Note(F, 1), new Scale(F, new int[]{1,1,1,0,1,1,1}));
		MAJOR_SCALES.put(new Note(G, -1), new Scale(G, new int[]{-1,-1,-1,-1,-1,-1,0}));
		MAJOR_SCALES.put(new Note(G, 0), new Scale(G, new int[]{0,0,0,0,0,0,1}));
		MAJOR_SCALES.put(new Note(G, +1), new Scale(G, new int[]{1,1,1,1,1,1,2}));
		MAJOR_SCALES.put(new Note(A, -1), new Scale(A, new int[]{-1,-1,0,-1,-1,0,0}));
		MAJOR_SCALES.put(new Note(A, 0), new Scale(A, new int[]{0,0,1,0,0,1,1}));
		MAJOR_SCALES.put(new Note(A, 1), new Scale(A, new int[]{1,1,2,1,1,2,2}));
		MAJOR_SCALES.put(new Note(B, -1), new Scale(B, new int[]{-1,0,0,-1,0,0,0}));
		MAJOR_SCALES.put(new Note(B, 0), new Scale(B, new int[]{0,1,1,0,1,1,1}));
		MAJOR_SCALES.put(new Note(B, 1), new Scale(B, new int[]{1,2,2,1,2,2,2}));
		MAJOR_SCALES.put(new Note(C, -1), new Scale(C, new int[]{-1,-1,-1,-1,-1,-1,-1}));
	}
	
	public static Scale getMajorScale(Note root) {
		return MAJOR_SCALES.get(root);
	}
	
	public String toString() {
		String str = "";
		for (int i = 0; i<notes.length-1; ++i)
			str += notes[i] + ", ";
		str += notes[notes.length-1];
		return str;
	}
	
	public Note[] applyIntervals(Interval... ints) {
		Note[] notes = new Note[ints.length];
		for (int i = 0; i<ints.length; ++i)
			notes[i] = applyInterval(ints[i]);
		return notes;
	}
	
	public Note applyInterval(Interval interval) {
		return notes[interval.major].modify(interval.modifier);
	}
	
	public Note getNote(int majorInterval) {
		return notes[majorInterval - 1];
	}

	private Scale(NoteName root, int[] modifiers) {
		notes = new Note[modifiers.length];
		int octave = 0;
		for (int i = 0; i<notes.length; ++i) {
            NoteName note = root.offset(i);
			if (i > 0 && note == NoteName.C)
				++octave;
			notes[i] = new Note(note, modifiers[i], octave);
		}
	}
	
	private Note[] notes;
}
