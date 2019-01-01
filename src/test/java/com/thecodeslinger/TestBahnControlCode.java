package com.thecodeslinger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestBahnControlCode {
    @Test
    public void nullInput() {
        assertFalse(BahnControlCode.isValid(null));
    }
    
     @Test
     public void emptyInput() {
         assertFalse(BahnControlCode.isValid(""));
     }
    
     @Test
     public void numberWithoutControlCode() {
         assertFalse(BahnControlCode.isValid("1234567"));
     }
    
     @Test
     public void numberTooShort() {
         assertFalse(BahnControlCode.isValid("1234-4"));
     }

     @Test
     public void invalidCode() {
         assertFalse(BahnControlCode.isValid("2341932-2"));
     }

     @Test
     public void validCode() {
         assertTrue(BahnControlCode.isValid("2341932-8"));
     }

     @Test
     public void validCodeEdgeCaseEvenSumOfDigits() {
         assertTrue(BahnControlCode.isValid("1341932-0"));
     }
}

