package rs.itekako.unittesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MockSpyDifferenceTest {

    @Mock
    private ArrayList<String> mockedListAnnotation;

    @Spy
    private ArrayList<String> spyListAnnotation;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Mock example")
    public void mockExample() {
        List<String> list = Mockito.mock(ArrayList.class);

        list.add("itekako");
        Mockito.verify(list).add("itekako");

        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("Mock example with annotations")
    public void mockExampleAnnotation() {

        mockedListAnnotation.add("itekako");
        Mockito.verify(mockedListAnnotation).add("itekako");

        assertEquals(0, mockedListAnnotation.size());
    }

    @Test
    @DisplayName("Spy example")
    public void spyExample() {
        List<String> list = Mockito.spy(ArrayList.class);

        list.add("itekako");
        Mockito.verify(list).add("itekako");

        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("Spy example with annotations")
    public void spyExampleAnnotation() {

        spyListAnnotation.add("itekako");
        Mockito.verify(spyListAnnotation).add("itekako");

        assertEquals(1, spyListAnnotation.size());
    }

}
