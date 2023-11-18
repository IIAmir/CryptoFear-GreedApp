package com.iiamir.cryptofeargreed.utils

object Constants {

    const val APP_NAME = "Crypto Fear & Greed"

    const val BASE_URL = "https://api.alternative.me/"
    const val REQUEST_QUERY = "limit"
    const val DATABASE_NAME = "fg_data_db"

    const val PREFERENCES = "app_pref"
    const val IS_IN_DARK_MODE_PREF_KEY = "is_in_dark_mode"
    const val LAST_YEAR_INDEX_IS_GONE_PREF_KEY = "is_gone"

    const val GET_ALL_FEAR_GREED = 0
    const val GET_NEXT_UPDATE_TIME_STAMP = 1

    const val GET_TODAY_FG_BY_INDEX = 0
    const val GET_YESTERDAY_FG_BY_INDEX = 1
    const val GET_LAST_WEEK_FG_BY_INDEX = 7
    const val GET_LAST_MONTH_FG_BY_INDEX = 30
    const val GET_LAST_YEAR_FG_BY_INDEX = 365

    const val FAILURE_REASON = "FAILURE_REASON"
    const val NULL_GLANCE_ID = "NULL_GLANCE_ID"
    const val NONE = "NONE"
    const val FAILED_REQUEST = "FAILED_REQUEST"

    const val HOME_SCREEN_ROUTE = "home_screen"
    const val SPLASH_SCREEN_ROUTE = "splash_screen"
    const val SEARCH_SCREEN_ROUTE = "search_screen"

    const val EXTREME_FEAR = "Extreme Fear"
    const val FEAR = "Fear"
    const val NEUTRAL = "Neutral"
    const val GREED = "Greed"
    const val EXTREME_GREED = "Extreme Greed"

    const val SHOW_UNKNOWN_ERROR = "Oops, something went wrong!"
    const val SHOW_NETWORK_ERROR_MESSAGE = "Couldn't reach server,check your internet connection."
    const val SHOW_SCREEN_NETWORK_ERROR_MESSAGE = "Check Your Internet Connection!"
    const val SHOW_PIE_CHART_ALERT = "Refresh,To Calculate Data."

    const val RETRY_TXT = "Try Again"
    const val YESTERDAY_TXT = "Yesterday"
    const val LAST_WEEK_TXT = "Last Week"
    const val LAST_MONTH_TXT = "Last Month"
    const val LAST_YEAR_TXT = "Last Year"
    const val NOW_INDEX_VALUE_TXT = "Now: "
    const val NEXT_UPDATE_TXT = "The next update will happen in:"
    const val VIEW_HISTORICAL_DATA = "View Historical Data"
    const val ONE_MONTH_FEAR_N_GREED_INDEX_TXT = "One Month Fear & Greed Index."

    const val BAR_CHART = "BarChart"
    const val LINE_CHART = "LineChart"

    const val EXTREME_FEAR_INDEX_VALUE_CODE = 0xFFE63300
    const val FEAR_INDEX_VALUE_CODE = 0xFFE77C00
    const val NEUTRAL_INDEX_VALUE_CODE = 0xFFE7C900
    const val GREED_INDEX_VALUE_CODE = 0xFF84E900
    const val EXTREME_GREED_INDEX_VALUE_CODE = 0xFF34E600

    const val IS_IN_DARK_MODE = "Light Mode"
    const val IS_IN_Light_MODE = "Dark Mode"
    const val SEND_FEEDBACK = "Send Feedback"
    const val RATE_APP = "Rate App"
    const val EXIT_APP = "Exit App"
    const val ABOUT = "About"
    const val SHOW_LAST_YEAR_INDEX = "Last Year Index"
    const val TOP_BAR_WIDGET_TEXT = "Crypto\nFear And Greed"
    const val ERROR_OCCURRED_WIDGET_TEXT = "Error Occurred!\nRefresh Widget."

    const val REFRESH_WIDGET = "Refresh"

    const val APP_ICON = "App Icon"
    const val TOGGLE_BUTTON = "Toggle Icon"
    const val SCROLL_ACTION_BUTTON = "Scroll Up"

    const val DISMISS_DIALOG = "Dismiss Dialog"

    const val ABOUT_TITLE = "Why Measure Fear and Greed?"
    const val ABOUT_CONTENT_1 =
        "The crypto market behaviour is very emotional. People tend to get greedy when the market is rising which results in FOMO (Fear of missing out). Also, people often sell their coins in irrational reaction of seeing red numbers. With our Fear and Greed Index, we try to save you from your own emotional overreactions. There are two simple assumptions:"
    const val ABOUT_CONTENT_2 =
        "Extreme fear can be a sign that investors are too worried. That could be a buying opportunity."
    const val ABOUT_CONTENT_3 =
        "When Investors are getting too greedy, that means the market is due for a correction."
    const val ABOUT_CONTENT_4 =
        "Therefore, we analyze the current sentiment of the Bitcoin market and crunch the numbers into a simple meter from 0 to 100. Zero means \"Extreme Fear\", while 100 means \"Extreme Greed\". See below for further information on our data sources."
    const val MORE_INFORMATION = "For More Information"

    const val PACKAGE_NAME = "com.iiamir.cryptofeargreed"
    const val SHOW_EMAIL_APPLICATIONS = "mailto:"
    const val SHOW_GOOGLE_PLAY = "https://play.google.com/store/apps/details?id="
    const val MARKET_URL = "market://details?id="
    const val DEVELOPER_EMAIL = "developer.iiamir@gmail.com"
    const val SHOW_ALTER_NATIVE_WEBSITE = "https://alternative.me/crypto/fear-and-greed-index/"

    // Version 2

    const val SEARCH_HERE_TXT = "Search Here..."
    const val NOT_FOUND_TXT = "Not Found!"
    const val ALL = "All"

    const val CLOSE_ICON = "Close Icon"
    const val SEARCH_ICON = "Search Icon"
    const val KEYBOARD_ARROW_RIGHT_ICON = "Arrow Right Icon"
    const val KEYBOARD_ARROW_DOWN_ICON = "Arrow Down Icon"

}