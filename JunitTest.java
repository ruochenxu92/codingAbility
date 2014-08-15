package leetcode;


	import java.util.*;
	import junit.framework.*;

	//So we can see the list objects being created,
	//and keep track of when they are cleaned up:
	class CountedList extends ArrayList {
	  private static int counter = 0;

	  private int id = counter++;

	  public CountedList() {
	    System.out.println("CountedList #" + id);
	  }

	  public int getId() {
	    return id;
	  }
	}

	public class JunitTest extends TestCase {
	  private CountedList list = new CountedList();

	  // You can use the constructor instead of setUp():
	  public JunitTest(String name) {
	    super(name);
	    for (int i = 0; i < 3; i++)
	      list.add("" + i);
	  }

	  // Thus, setUp() is optional, but is run right
	  // before the test:
	  protected void setUp() {
	    System.out.println("Set up for " + list.getId());
	  }

	  // tearDown() is also optional, and is called after
	  // each test. setUp() and tearDown() can be either
	  // protected or public:
	  public void tearDown() {
	    System.out.println("Tearing down " + list.getId());
	  }

	  // All tests have method names beginning with "test":
	  public void testInsert() {
	    System.out.println("Running testInsert()");
	    assertEquals(list.size(), 3);
	    list.add(1, "Insert");
	    assertEquals(list.size(), 4);
	    assertEquals(list.get(1), "Insert");
	  }

	  public void testReplace() {
	    System.out.println("Running testReplace()");
	    assertEquals(list.size(), 3);
	    list.set(1, "Replace");
	    assertEquals(list.size(), 3);
	    assertEquals(list.get(1), "Replace");
	  }

	  // A "helper" method to reduce code duplication. As long
	  // as the name doesn't start with "test," it will not
	  // be automatically executed by JUnit.
	  private void compare(ArrayList lst, String[] strs) {
	    Object[] array = lst.toArray();
	    assertTrue("Arrays not the same length", array.length == strs.length);
	    for (int i = 0; i < array.length; i++)
	      assertEquals(strs[i], (String) array[i]);
	  }

	  public void testOrder() {
	    System.out.println("Running testOrder()");
	    compare(list, new String[] { "0", "1", "2" });
	  }

	  public void testRemove() {
	    System.out.println("Running testRemove()");
	    assertEquals(list.size(), 3);
	    list.remove(1);
	    assertEquals(list.size(), 2);
	    compare(list, new String[] { "0", "2" });
	  }

	  public void testAddAll() {
	    System.out.println("Running testAddAll()");
	    list.addAll(Arrays.asList(new Object[] { "An", "African", "Swallow" }));
	    assertEquals(list.size(), 6);
	    compare(list,
	        new String[] { "0", "1", "2", "An", "African", "Swallow" });
	  }

	  public static void main(String[] args) {
	    // Invoke JUnit on the class:
	    junit.textui.TestRunner.run(JunitTest.class);
	  }
	} ///:~


