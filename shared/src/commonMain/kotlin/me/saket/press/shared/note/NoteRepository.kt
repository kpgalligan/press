package me.saket.press.shared.note

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.map
import com.benasher44.uuid.Uuid
import me.saket.press.data.shared.Note
import me.saket.press.shared.util.Koptional

interface NoteRepository {
  fun note(noteUuid: Uuid): Observable<Koptional<Note>>
  fun notes(includeEmptyNotes: Boolean): Observable<List<Note>>
  fun create(vararg insertNotes: InsertNote): Completable
  fun update(noteUuid: Uuid, content: String): Completable
  fun markAsDeleted(noteUuid: Uuid): Completable

  fun create(noteUuid: Uuid, content: String): Completable {
    return create(InsertNote(noteUuid, content))
  }
}
