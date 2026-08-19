package com.github.leandrolimasi.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pattern: <b>JVM/language-semantics gotchas</b> (not an algorithm &mdash; these are executable
 * notes on Java runtime behavior that regularly trips people up in interviews and in production).
 *
 * <p>Each test below demonstrates one specific rule of the language so it's easy to look up later:
 * string interning and reference vs. value equality, autoboxing's {@code Integer} cache, and
 * NullPointerException from unboxing a null wrapper.
 */
public class TrickyTest {

  @Test
  @DisplayName("String literals are interned (shared) but 'new String(...)' always allocates")
  public void testCaseStringPoolAndObjects() {
    // Pattern: string interning / the string constant pool.
    // Literals with the same content are the *same* object from the pool, so == (reference
    // equality) is true for them. new String(...) forces a fresh heap allocation, so == is false
    // even though .equals (value equality) is still true.
    String a = "test";
    String b = "test";
    String c = new String("test");
    String d = new String("test");
    assertTrue(a == b);
    assertTrue(a.equals(b));
    assertFalse(c == a);
    assertTrue(c.equals(a));
    assertFalse(c == d);
    assertTrue(c.equals(d));
  }

  @Test
  @DisplayName("Unboxing a null Integer to an int throws NullPointerException")
  public void testCaseUnboxingNull() {
    // Pattern: implicit unboxing. Assigning a null wrapper (Integer) to a primitive (int) compiles
    // fine, but the compiler-generated call to Integer.intValue() on a null reference throws NPE
    // at runtime. This is a common cause of surprise NPEs when mixing wrapper and primitive types
    // (e.g. a Map<String, Integer> lookup miss auto-unboxed into an int).
    Integer a = null;
    assertThrows(NullPointerException.class, () -> {
      int b = a;
    });
  }

  @Test
  @DisplayName("Autoboxed Integers in [-128,127] are cached and share identity; outside that range they don't")
  public void testCaseIntegerCache() {
    // Pattern: the Integer autoboxing cache (JLS 5.1.7). Boxing int -> Integer for values in
    // [-128, 127] reuses cached instances (same rule Byte, Short, Long and Character follow for
    // their equivalent small ranges), so == happens to work for them. Outside that range each
    // boxing operation may allocate a new object, so == is no longer reliable and .equals must be
    // used instead. This is why "==" on Integers looks like it works in small examples but breaks
    // once the values get larger.
    Integer withinCacheA = 100;
    Integer withinCacheB = 100;
    assertTrue(withinCacheA == withinCacheB);

    Integer outsideCacheA = 200;
    Integer outsideCacheB = 200;
    assertFalse(outsideCacheA == outsideCacheB);
    assertTrue(outsideCacheA.equals(outsideCacheB));
  }
}
