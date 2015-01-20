package com.jdydev.exercise;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StringManipulationTest {
    StringManipulation sm = new StringManipulation();
    String foo = "foo";
    String bar = "bar";

    @Test
    public void testConcatNull() {
        assertThat(null, equalTo(sm.concatList(null)));
    }

    @Test
    public void testConcatEmptyList() {
        assertThat("", equalTo(sm.concatList(new ArrayList<String>())));
    }

    @Test
    public void testConcatOneItem() {
        List<String> l = new ArrayList<String>();
        l.add(foo);
        assertThat(foo, equalTo(sm.concatList(l)));
    }

    @Test
    public void testConcatTwoItems() {
        List<String> l = new ArrayList<String>();
        l.add(foo);
        l.add(bar);
        assertThat(foo + bar, equalTo(sm.concatList(l)));
    }

    @Test
    public void testConcatTwoItemsWithNull() {
        List<String> l = new ArrayList<String>();
        l.add(foo);
        l.add(null);
        l.add(bar);
        assertThat(foo + bar, equalTo(sm.concatList(l)));
    }

    @Test
    public void testConcatTwoItemsWithEmpty() {
        List<String> l = new ArrayList<String>();
        l.add(foo);
        l.add("");
        l.add(bar);
        assertThat(foo + bar, equalTo(sm.concatList(l)));
    }

    @Test(timeout = 100)
    // This forces the implementation to be fast
    public void testConcatManyItems() {
        List<String> l = new ArrayList<String>(20000);
        for (int i = 0; i < 10000; i++) {
            l.add(foo);
            l.add(bar);
        }
        assertTrue(sm.concatList(l).matches("^(foobar)+$"));
    }

    @Test
    public void testNullGetFirstThree() {
        assertThat(null, equalTo(sm.getFirstThree(null)));
    }

    @Test
    public void testEmptyGetFirstThree() {
        assertThat("", equalTo(sm.getFirstThree("")));
    }

    @Test
    public void testThreeGetFirstThree() {
        assertThat(foo, equalTo(sm.getFirstThree(foo)));
    }

    @Test
    public void testMoreGetFirstThree() {
        assertThat(foo, equalTo(sm.getFirstThree(foo + bar)));
    }

    @Test
    public void testGetFirstOne() {
        assertThat("f", equalTo(sm.getFirstX(1, foo)));
    }

    @Test
    public void testParseBooleanNull() {
        assertThat(false, equalTo(sm.parseBoolean(null, false)));
    }

    @Test
    public void testParseBooleanEmpty() {
        assertThat(false, equalTo(sm.parseBoolean("", false)));
    }

    @Test
    public void testParseBooleanFoo() {
        assertThat(false, equalTo(sm.parseBoolean(foo, false)));
    }

    @Test
    public void testParseBooleanTrue() {
        assertThat(true, equalTo(sm.parseBoolean("true", false)));
    }

    @Test
    public void testParseBooleanFalse() {
        assertThat(false, equalTo(sm.parseBoolean("false", true)));
    }

    @Test
    public void testParseBooleanFalse2() {
        assertThat(false, equalTo(sm.parseBoolean("fAlse", true)));
    }
}
