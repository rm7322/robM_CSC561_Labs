/**
 * Unit testing for simple timer module, it tests:
 * 
 * 		initializes correctly
 * 		updates time correctly
 * 		able to add observers
 * 		able to remove observers
 * 		observers recieve time update
 * 		updates time correctly as thread
 * 		able to sleep the thread
 * 		uses a mock object to test observable
 * 
 * @Author: Rob Miles
 */

package gameplay;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestSimpleTimer
{

	@Test // initializes correctly
	public void testInit()
	{
		SimpleTimer DrZoidberg = new SimpleTimer();
		assertTrue(DrZoidberg instanceof Timer);
		assertEquals(0, DrZoidberg.getCurrentTime());
	}
	
	@Test //updates time @constructor.. [counter method tested later for more complex update method]
	public void testUpdateTime()
	{
		SimpleTimer DrZoidberg = new SimpleTimer();
		assertEquals(0, DrZoidberg.getCurrentTime());
	}

	@Test
	public void testAddTimeObserver()
	{
		TimeObserver newObserver = new MockSimpleTimerObserver();
		SimpleTimer masterTimer = new SimpleTimer();
		masterTimer.addTimeObserver(newObserver);
		assertEquals(newObserver, masterTimer.getObserver());
	}

	/**
	 * make sure we can remove the time observer ListArray will then be empty so we
	 * look to observe the exception that it can't return anything
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveTimeObserver()
	{
		TimeObserver newObserver = new MockSimpleTimerObserver();
		SimpleTimer masterTimer = new MockSimpleTimer();
		masterTimer.addTimeObserver(newObserver);
		masterTimer.removeTimeObserver(newObserver);
		assertEquals(newObserver, masterTimer.getObserver());
	}

	/*
	 * Develop a test to make sure timeChanged works correctly with and without
	 * observers. Depending on whether theObservers is protected or private you may
	 * have this test in place after doing tests for 2 and 3.
	 * 
	 * Here we override the time counter of the SimpletimerClass and push that to
	 * the Obervers [we test the counter later so this test compliments it]. Mock
	 * observer class just uses additonal getter/setter methods to return test
	 * results but otherwise utilizes (and tests) the main methods
	 */
	@Test
	public void testTimeChanged()
	{

		MockSimpleTimer masterTimer = new MockSimpleTimer(); // just so we can override currentTime
		TimeObserver newObserver1 = new MockSimpleTimerObserver();
		TimeObserver newObserver2 = new MockSimpleTimerObserver();
		TimeObserver newObserver3 = new MockSimpleTimerObserver();
		masterTimer.addTimeObserver(newObserver1);
		masterTimer.addTimeObserver(newObserver2);
		masterTimer.addTimeObserver(newObserver3);
		masterTimer.overrideCurrentTime(); // method only for testing
		masterTimer.timeChanged();
		assertEquals(3, ((MockSimpleTimerObserver) newObserver3).getTime());
		assertEquals(3, ((MockSimpleTimerObserver) newObserver2).getTime());
		assertEquals(3, ((MockSimpleTimerObserver) newObserver1).getTime());
	}

	/**
	 * This tests that SimpleTimer will update time once every second. we have a
	 * 250ms delay because you don't know wether the thread for the timer is
	 * slightly ahead of the test/check or vice versa
	 */
	@Test
	public void testSimpleTimerAsThread() throws InterruptedException
	{
		SimpleTimer st = new SimpleTimer(1000);
		st.start();
		Thread.sleep(250);
		// So we are 1/4th a second different
		for (int x = 0; x < 5; x++)
		{
			assertEquals(x, st.getCurrentTime()); // assumes round starts at 0
			Thread.sleep(1000); // wait for the next time change
		}
	}

	//test mock time observer
	@Test
	public void testMockTimeObserver()
	{

		MockSimpleTimer masterTimer = new MockSimpleTimer(); // just so we can override currentTime
		TimeObserver newObserver1 = new MockSimpleTimerObserver();
		masterTimer.addTimeObserver(newObserver1);
		masterTimer.overrideCurrentTime(); // method only for testing
		masterTimer.timeChanged();
		assertEquals(3, ((MockSimpleTimerObserver) newObserver1).getTime());
	}
	
	

}
class MockSimpleTimerObserver implements TimeObserver
{
	public int myTime = 0;

	public void update(int time)
	{
		myTime = time;
	}

	public int getTime()
	{
		return myTime;
	}

}
class MockSimpleTimer extends SimpleTimer
{
	// @Override
	public void overrideCurrentTime()
	{
		currentTime = 3;
	}
}
