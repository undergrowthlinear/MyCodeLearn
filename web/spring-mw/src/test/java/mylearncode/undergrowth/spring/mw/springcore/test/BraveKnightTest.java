package mylearncode.undergrowth.spring.mw.springcore.test;

import org.junit.Test;
import static org.mockito.Mockito.*;

import mylearncode.undergrowth.spring.mw.springinaction.springcore.BraveKnight;
import mylearncode.undergrowth.spring.mw.springinaction.springcore.Quest;

public class BraveKnightTest {

	@Test
	public void knightShouldOnQuest() {
		Quest mockquest = mock(Quest.class);
		BraveKnight knight = new BraveKnight(mockquest);
		knight.embarkOnQuest();
		verify(mockquest, times(1)).embark();
	}

}
