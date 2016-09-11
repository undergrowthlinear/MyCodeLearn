package mylearncode.undergrowth.spring.mw.springcore.test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.mockito.InOrder;

public class MockitoTest {

	@Test
	public void listMockTest() {
		List<String> mockedList = mock(List.class);

		mockedList.add("mockString");
		mockedList.clear();

		verify(mockedList).add("mockString");
		verify(mockedList).clear();
	}

	@Test
	public void stubMockTest() {
		LinkedList<String> mockedList = mock(LinkedList.class);

		when(mockedList.get(0)).thenReturn("first");
		// when(mockedList.get(1)).thenThrow(new
		// IllegalArgumentException("illegal
		// argument"));
		when(mockedList.get(1)).thenReturn("second");

		System.out.println(mockedList.get(0));
		System.out.println(mockedList.get(1));
		System.out.println(mockedList.get(999));

		verify(mockedList).get(0);
	}

	@Test
	public void arguMatchTest() {
		LinkedList<String> mockedList = mock(LinkedList.class);

		when(mockedList.get(anyInt())).thenReturn("element");

		System.out.println(mockedList.get(0));
		System.out.println(mockedList.get(1));
		System.out.println(mockedList.get(999));
		// verify(mockedList).get(anyInt());
		verify(mockedList).get(0);
	}

	@Test
	public void timesTest() {
		LinkedList<String> mockedList = mock(LinkedList.class);

		mockedList.add("once");

		mockedList.add("twice");
		mockedList.add("twice");

		mockedList.add("three times");
		mockedList.add("three times");
		mockedList.add("three times");

		// following two verifications work exactly the same - times(1) is used
		// by default
		verify(mockedList).add("once");
		verify(mockedList, times(1)).add("once");

		// exact number of invocations verification
		verify(mockedList, times(2)).add("twice");
		verify(mockedList, times(3)).add("three times");

		// verification using never(). never() is an alias to times(0)
		verify(mockedList, never()).add("never happened");

		// verification using atLeast()/atMost()
		verify(mockedList, atLeastOnce()).add("three times");
		verify(mockedList, atLeast(2)).add("three times");
		verify(mockedList, atMost(5)).add("three times");
	}

	@Test
	public void inOrderTest() {
		List singleMock = mock(List.class);

		// using a single mock
		singleMock.add("was added first");
		singleMock.add("was added second");

		// create an inOrder verifier for a single mock
		InOrder inOrder = inOrder(singleMock);

		// following will make sure that add is first called with "was added
		// first, then with "was added second"
		inOrder.verify(singleMock).add("was added first");
		inOrder.verify(singleMock).add("was added second");

		// B. Multiple mocks that must be used in a particular order
		List firstMock = mock(List.class);
		List secondMock = mock(List.class);

		// using mocks
		firstMock.add("was called first");
		secondMock.add("was called second");

		// create inOrder object passing any mocks that need to be verified in
		// order
		inOrder = inOrder(firstMock, secondMock);

		// following will make sure that firstMock was called before secondMock
		inOrder.verify(firstMock).add("was called first");
		inOrder.verify(secondMock).add("was called second");
	}

}
