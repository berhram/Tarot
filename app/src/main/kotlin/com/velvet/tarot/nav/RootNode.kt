package com.velvet.tarot.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.pop
import com.bumble.appyx.navmodel.backstack.operation.push
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackSlider
import com.velvet.tarot.card.CardNode
import com.velvet.tarot.feed.FeedNode

class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<Routing> = BackStack(
        Routing.FeedScreen,
        buildContext.savedStateMap
    )
) : ParentNode<Routing>(
    backStack,
    buildContext
) {

    override fun resolve(routing: Routing, buildContext: BuildContext): Node = when (routing) {
        is Routing.FeedScreen -> FeedNode(buildContext) { backStack.push(Routing.CardScreen(it)) }
        is Routing.CardScreen -> CardNode(buildContext, routing.cardId) { backStack.pop() }
    }

    @Composable
    override fun View(modifier: Modifier) {
        Children(
            navModel = backStack,
            transitionHandler = rememberBackstackSlider()
        )
    }
}