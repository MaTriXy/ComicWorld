package org.firezenk.comicworld.ui.features.characters

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.firezenk.comicworld.ui.features.characters.di.CharactersModule.Companion.CHARACTER_ROUTE
import org.firezenk.comicworld.ui.features.commons.Presenter
import org.firezenk.kartographer.library.Kartographer
import org.firezenk.kartographer.library.types.ViewRoute
import javax.inject.Inject
import javax.inject.Named

class CharactersPresenter @Inject constructor(router: Kartographer,
                                              @Named(CHARACTER_ROUTE) private val characterRoute: ViewRoute,
                                              private val states: CharactersStates)
    : Presenter<CharactersActions, CharactersStates, CharactersView>(router) {

    override fun reduce(action: CharactersActions) {
        when(action) {
            is CharactersActions.LoadCharacters -> launch(UI) {
                action.getCharacters.execute().run {
                    render(states.success(this))
                }
            }
            is CharactersActions.OpenCharacter -> {
                router.next(characterRoute, mapOf("id" to action.id))
            }
        }
    }
}