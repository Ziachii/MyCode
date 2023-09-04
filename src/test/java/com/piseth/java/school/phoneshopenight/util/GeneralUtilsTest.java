package com.piseth.java.school.phoneshopenight.util;
import com.piseth.java.school.phoneshopenight.utils.GeneralUtils;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneralUtilsTest {

    @Test
    public void testToIntegerList(){
        //given(have to input param of string)
        List<String> names = List.of("Dara","Cheata","Thida");

        //when (when function have called)
        List<Integer> list = GeneralUtils.toIntegerList(names);

        //then (expectation the result)
        assertEquals(3,list.size());
        assertEquals(4,list.get(0));
        assertEquals(6,list.get(1));
        assertEquals(5,list.get(2));

    }
    @Test
    public void getEvenNumber(){
        //given
        List<Integer> list = List.of(4,5,3,20,6,8);
        //when
        List<Integer> evenNumbers = GeneralUtils.getEvenNumber(list);
        //then
        assertEquals(4, evenNumbers.size());
        assertEquals(4,evenNumbers.get(0));

    }
}
