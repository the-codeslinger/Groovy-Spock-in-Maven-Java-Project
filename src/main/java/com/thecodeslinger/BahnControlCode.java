package com.thecodeslinger;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.IntStream;

public class BahnControlCode {
    
    public static boolean isValid(String trainNumber) {
        if (!isValidInput(trainNumber)) {
            return false;
        }
        
        List<Integer> numbers = getTrainNumbersList(trainNumber);
        int controlCode = getControlCode(trainNumber);
        int computedCode = computeControlCodeStream(numbers);
        
        return controlCode == computedCode;
    }
    
    private static List<Integer> getTrainNumbersList(String trainNumber) {
        String[] numbersControlCode = trainNumber.split("-");
        return numbersControlCode[0].codePoints()
               .mapToObj(c -> Character.toString((char) c))
               .map(Integer::parseInt)
               .collect(toList());
    }
    
    private static int getControlCode(String trainNumber) {
        String[] numbersControlCode = trainNumber.split("-");
        return Integer.parseInt(numbersControlCode[1]);
    }
    
    private static boolean isValidInput(String trainNumbers) {
        if (null == trainNumbers) {
            return false;
        }
        if (trainNumbers.isEmpty()) {
            return false;
        }
        return trainNumbers.matches("^[1-9]{7}-\\d{1}$");
    }
    
    private static int computeControlCodeStream(List<Integer> numbers) {
        int sumOfDigits = IntStream.range(0, numbers.size())
                .map(index -> intermediateNumber(numbers, index))
                .map(BahnControlCode::sumOfDigits)
                .sum();

        int computedCode = 0;
        if (sumOfDigits % 10 != 0) {
            computedCode = 10 - (sumOfDigits % 10);
        }
        return computedCode;
    }
    
    private static int sumOfDigits(int value) {
        int sumOfDigits = value % 10;
        if (10 <= value) {
            sumOfDigits++;
        }
        return sumOfDigits;
    }
    
    private static int intermediateNumber(List<Integer> numbers, int index) {
        int factor = index % 2 == 0 ? 2 : 1;
        return numbers.get(index) * factor;
    }

}
