package me.saket.press.shared.rx

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.map
import com.badoo.reaktive.observable.observableInterval
import com.badoo.reaktive.observable.withLatestFrom
import com.badoo.reaktive.scheduler.Scheduler
import com.soywiz.klock.TimeSpan
import me.saket.press.shared.util.Koptional
import me.saket.press.shared.util.toOptional

internal fun <T, R : Any> Observable<T>.mapToOptional(mapper: (T) -> R?): Observable<Koptional<R>> {
  return map { mapper(it).toOptional() }
}

internal fun <T : Any> Observable<Koptional<T>>.mapToSome(): Observable<T> {
  return map { (item) -> item!! }
}

internal fun observableInterval(interval: TimeSpan, scheduler: Scheduler): Observable<Long> {
  return observableInterval(interval.milliseconds.toLong(), scheduler)
}
