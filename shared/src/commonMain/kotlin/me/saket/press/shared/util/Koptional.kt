package me.saket.press.shared.util;

import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

/**
 * Copied from https://github.com/gojuno/koptional
 */
sealed class Koptional<out T : Any> {

    /**
     * Converts [Koptional] to either its non-null value if it's [Some] or `null` if it's [None].
     */
    abstract fun toNullable(): T?

    /**
     * Unwraps this optional into the value it holds or null if there is no value held.
     */
    @JvmSynthetic
    abstract operator fun component1(): T?

    companion object {

        /**
         * Wraps an instance of T (or null) into an [Koptional]:
         *
         * ```java
         * String a = "str";
         * String b = null;
         *
         * Optional<String> optionalA = Optional.toOptional(a); // Some("str")
         * Optional<String> optionalB = Optional.toOptional(b); // None
         * ```
         *
         * This is the preferred method of obtaining an instance of [Koptional] in Java. In Kotlin,
         * prefer using the [toOptional][com.gojuno.koptional.toOptional] extension function.
         */
        @JvmStatic
        fun <T : Any> toOptional(value: T?): Koptional<T> = if (value == null) None else Some(value)
    }
}

data class Some<out T : Any>(val value: T) : Koptional<T>() {
    override fun toString() = "Some($value)"
    override fun toNullable(): T = value
}

object None : Koptional<Nothing>() {
    override fun toString() = "None"

    override fun component1(): Nothing? = null

    override fun toNullable(): Nothing? = null
}

/**
 * Wraps an instance of T (or null) into an [Koptional]:
 *
 * ```kotlin
 * val a: String? = "str"
 * val b: String? = null
 *
 * val optionalA = a.toOptional() // Some("str")
 * val optionalB = b.toOptional() // None
 * ```
 *
 * This is the preferred method of obtaining an instance of [Koptional] in Kotlin. In Java, prefer
 * using the static [Koptional.toOptional] method.
 */
fun <T : Any> T?.toOptional(): Koptional<T> = if (this == null) None else Some(this)