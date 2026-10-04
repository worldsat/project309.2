package com.makancompany.project309.ui.tracker

sealed interface TrackerAction {
    data object GetDirectionsClicked : TrackerAction
    data object CallStoreClicked : TrackerAction
    data class RedeemRewardClicked(val rewardId: String) : TrackerAction
    data object DownloadReceiptClicked : TrackerAction
    data object NeedHelpClicked : TrackerAction
    data object DismissMessage : TrackerAction
    data object MoreOptionsClicked : TrackerAction
    data class StepChanged(val step: TrackerStep) : TrackerAction
}
