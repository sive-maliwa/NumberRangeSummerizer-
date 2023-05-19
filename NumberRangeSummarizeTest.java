package numberrangessummarizer;;
import junit.framework.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals
import  org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runner.Suite;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Suite.class)
public class NumberRangeSummarizerTest {

    @Autowired
    private NumberRangeSummarizer numberRangeSummarizer;

    @Test
    public void testCollectionSize() {
        Assert.assertEquals(numberRangeSummarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31").size(), 14);
    }

    @Test
    public void testStringIsSplit() {
        Collection<Integer> numbers = numberRangeSummarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        Assert.assertFalse(numbers.isEmpty());
        Assert.assertEquals(numbers.iterator().hasNext(), true);
    }

    @Test
    public void testCollectionIsSequential() {

        Collection<Integer> numbers = numberRangeSummarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
         Assert.assertFalse(Boolean.parseBoolean(Array.stream(numbers.toArray()).unordered().toString()));
    }

    @Test
    public void testNumbersSummarazation() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        String expected = "1,3,6-8,12-15,21-24,31";

        Collection<Integer> numbers = numberRangeSummarizer.collect(input);

        Assert.assertEquals(numberRangeSummarizer.summarizeCollection(numbers), expected);
    }
		
}
