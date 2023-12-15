package org.taxi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompletionTest {
    private Taxi t1;
    @BeforeEach
    void Setup() {
        t1 = new Taxi("RAWR");
    }

    @Test 
    void testGetRatingWhenNoRating() {
        new Completion(t1, 5);
        assertEquals(5, t1.getRating());
    }

    @Test 
    void testSetRatingWhenNoRating() {
        new Completion(t1, 5);
        assertEquals(1, t1.getTotalRatings());
    }

    @Test 
    void testGetRatingWithRating() {
        new Completion(t1, 5);
        new Completion(t1, 3); 
        new Completion(t1, 4);

        assertEquals(4, t1.getRating());
    }

    @Test
    void testSetRatingWithRating() {
        new Completion(t1, 5);
        new Completion(t1, 3); 
        new Completion(t1, 4);

        assertEquals(3, t1.getTotalRatings());
    }
}
