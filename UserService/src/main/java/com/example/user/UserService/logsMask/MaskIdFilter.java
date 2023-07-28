//package com.example.user.UserService.logsMask;
//import org.apache.logging.log4j.core.LogEvent;
//import org.apache.logging.log4j.core.config.plugins.Plugin;
//import org.apache.logging.log4j.core.filter.AbstractFilter;
//
//@Plugin(name = "MaskIdFilter", category = "Core", elementType = "filter", printObject = true)
//public class MaskIdFilter extends AbstractFilter {
//
//    @Override
//    public Result filter(LogEvent event) {
//        String message = event.getMessage().getFormattedMessage();
//        String maskedMessage = message.replaceAll("getting the user with user id (\\d+)", "getting the user with user id ***$1****");
//        return onMatch(maskedMessage, null);
//    }
//
//    @Plugin.Factory
//    public static MaskIdFilter createFilter() {
//        return new MaskIdFilter();
//    }
//}
