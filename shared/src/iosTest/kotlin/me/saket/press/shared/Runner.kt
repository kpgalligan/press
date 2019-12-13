package me.saket.press.shared

/**
 * Still not fully sure how this works, but the common tests are on the JVM
 * using Android's JUnit runner so that an in-memory database can be created.
 * See [TestDatabase].
 *
 * Copied from https://github.com/russhwolf/soluna
 */
actual abstract class Runner

actual class AndroidJUnit4 : Runner()