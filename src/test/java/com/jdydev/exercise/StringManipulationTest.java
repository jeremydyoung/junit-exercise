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
        assertThat(sm.concatList(null), equalTo(null));
    }

    @Test
    public void testConcatEmptyList() {
        assertThat(sm.concatList(new ArrayList<String>()), equalTo(""));
    }

    @Test
    public void testConcatOneItem() {
        List<String> l = new ArrayList<String>();
        l.add(foo);
        assertThat(sm.concatList(l), equalTo(foo));
    }

    @Test
    public void testConcatTwoItems() {
        List<String> l = new ArrayList<String>();
        l.add(foo);
        l.add(bar);
        assertThat(sm.concatList(l), equalTo(foo + bar));
    }

    @Test
    public void testConcatTwoItemsWithNull() {
        List<String> l = new ArrayList<String>();
        l.add(foo);
        l.add(null);
        l.add(bar);
        assertThat(sm.concatList(l), equalTo(foo + bar));
    }

    @Test
    public void testConcatTwoItemsWithEmpty() {
        List<String> l = new ArrayList<String>();
        l.add(foo);
        l.add("");
        l.add(bar);
        assertThat(sm.concatList(l), equalTo(foo + bar));
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
        assertThat(sm.getFirstThree(null), equalTo(null));
    }

    @Test
    public void testEmptyGetFirstThree() {
        assertThat(sm.getFirstThree(""), equalTo(""));
    }

    @Test
    public void testThreeGetFirstThree() {
        assertThat(sm.getFirstThree(foo), equalTo(foo));
    }

    @Test
    public void testMoreGetFirstThree() {
        assertThat(sm.getFirstThree(foo + bar), equalTo(foo));
    }

    @Test
    public void testGetFirstOne() {
        assertThat(sm.getFirstX(1, foo), equalTo("f"));
    }

    @Test
    public void testParseBooleanNull() {
        assertThat(sm.parseBoolean(null, false), equalTo(false));
    }

    @Test
    public void testParseBooleanEmpty() {
        assertThat(sm.parseBoolean("", true), equalTo(true));
    }

    @Test
    public void testParseBooleanFoo() {
        assertThat(sm.parseBoolean(foo, true), equalTo(true));
    }

    @Test
    public void testParseBooleanTrue() {
        assertThat(sm.parseBoolean("true", false), equalTo(true));
    }

    @Test
    public void testParseBooleanFalse() {
        assertThat(sm.parseBoolean("false", true), equalTo(false));
    }

    @Test
    public void testParseBooleanFalse2() {
        assertThat(sm.parseBoolean("fAlse", true), equalTo(false));
    }
}
