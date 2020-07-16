package per.cby.collect.orbcomm.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 错误代码
 * 
 * @author chenboyang
 * @since 2020年4月20日
 *
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NO_ERRORS(0, "没有错误."),
    ERR_INVALID_MESSAGE(2, "You have tried to send a restricted message."),
    ERR_INVALID_MESSAGE_CONTENT(3, "Message content is not valid."),
    ERR_MT_SN_MISSING(4, "MT serial number is missing."),
    ERR_INVALID_MESSAGE_ID(14, "Unable to find a message with the specified ID."),
    ERR_UNKNOWN_ERROR_CODE(15, "Internal error - invalid error code value."),
    ERR_CUSTOMER_DELAYED(16, "Web service request rejected. Please check your profile for the right delay value."),
    ERR_INVALID_INPUT_PARAMETER_TIMESTAMP_FORMAT(17, "Invalid timestamp format."),
    ERR_SERVER_STOPPED(18, "Gateway cannot accept messages for this destination because one of its servers was stopped"),
    ERR_DB_ERROR(19, "Internal error - database access failed."),
    ERR_GATEWAY_NOT_READY(20, "Gateway not ready, please try again later."),
    ERR_INVALID_INPUT_DATA(23, "One of message or web service operation parameters is empty or invalid."),
    ERR_INVALID_CUSTOMER_GW_VAR_ID(40, "Invalid VAR destination ID specified."),
    ERR_INVALID_DESTINATION_ID(48, "Invalid or nonexistent VAR destination ID."),
    ERR_MESSAGE_CLOSED_BECAUSE_OF_TERMINAL_RESET(49, "Message closed because a terminal registration message was received."),
    ERR_INVALID_MT_SN_LENGTH(50, "Invalid MT serial number."),
    ERR_TOO_MANY_MESSAGES_REQUESTED(90, "Too many forward messages requested."),
    ERR_TOO_MANY_MESSAGES_QUEUED_FOR_SAME_TERMINAL(95, "Too many messages queued for the same terminal."),
    ERR_MAX_TERMINAL_COUNT_PER_CALL_EXCEEDED(98, "Maximum destination terminal count in this call exceeded."),
    ERR_MAX_MESSAGE_COUNT_EXCEEDED(100, "Maximum number of sent messages exceeded."),
    ERR_UNAUTHORIZED_FOR_THIS_OPERATION(104, "The user is not authorized for this gateway operation."),
    ERR_UNABLE_TO_CANCEL_THE_MESSAGE(115, "The message you wish to cancel cannot be cancelled any more."),
    ERR_INVALID_MESSAGE_XML_CONTENT(116, "Gateway is unable to parse the message content."),
    ERR_INTERNAL_EXCEPTION(128, "Internal Gateway exception."),
    ERR_INVALID_TERMINAL_ID_GENERAL(512, "Error code returned by a PPC or a terminal."),
    ERR_INVALID_TERMINAL_ID_NO_SUCH_TERMINAL(513, "Error code returned by a PPC or a terminal."),
    ERR_INVALID_TERMINAL_ID_NOT_YOUR_TERMINAL(514, "Error code returned by a PPC or a terminal."),
    ERR_TERMINAL_NOT_REGISTERED(516, "Error code returned by a PPC or a terminal."),
    ERR_TERMINAL_DISABLED_BY_PPC(517, "Error code returned by a PPC or a terminal."),
    ERR_WAKEUP_PERIOD_CHANGE_IN_PROGRESS(518, "Error code returned by a PPC or a terminal."),
    ERR_NOT_AUTHORIZED_TO_USE_THIS_MESSAGE(520, "Error code returned by a PPC or a terminal."),
    ERR_TERMINAL_REGULATORY_MUTED_BY_OPERATOR(522, "Error code returned by a PPC or a terminal."),
    ERR_CHANGING_PRIORITY_IN_PROGRESS(523, "Error code returned by a PPC or a terminal."),
    ERR_TERMINAL_NOT_ACTIVE_BECAUSE_BILLING_LEVEL_IS_ZERO(524, "Error code returned by a PPC or a terminal."),
    ERR_TERMINAL_BEAM_NUMBER_UNKNOWN(765, "Error code returned by a PPC or a terminal."),
    ERR_NO_EARTHSTATION_ROUTE_TO_TERMINAL(767, "Error code returned by a PPC or a terminal."),
    ERR_INVALID_MESSAGE_CONTENT_GENERAL(1024, "Error code returned by a PPC or a terminal."),
    ERR_PPC_PROCESSING_ERROR(1280, "Error code returned by a PPC or a terminal."),
    ERR_INVALID_USER_ID_OR_PASSWORD(1284, "Error code returned by a PPC or a terminal."),
    ERR_NDN_INVALID_BEAM(12308, "Non delivery notification reason: invalid beam number"),
    ERR_NDN_TIMED_OUT(12309, "Non delivery notification reason: message time out"),
    ERR_INVALID_BROADCAST_ID_NO_SUCH_ID(12310, "Invalid broadcast ID, no such ID in the system."),
    ERR_TERMINAL_IS_DISABLED(12311, "Mobile terminal is disabled."),
    ERR_BROADCAST_ID_IS_DISABLED(12312, "Broadcast ID is disabled."),
    ERR_INVALID_DESTINATION_ID_GENERAL(16896, "Invalid terminal ID - general"),
    ERR_INVALID_DESTINATION_ID_NO_SUCH_DESTINATION(16897, "Invalid terminal ID - no such terminal"),
    ERR_INVALID_DESTINATION_ID_NOT_YOUR_DESTINATION(16898, "Invalid terminal ID - not your terminal"),
    ERR_DESTINATION_NOT_REGISTERED(16900, "Terminal not registered"),
    ERR_NOT_AUTHORIZED_TO_USE_THE_MESSAGE(16904, "Not authorized to use the message"),
    ERR_TERMINAL_BEAM_UNKNOWN(17149, "Terminal beam unnown"),
    ERR_NO_EARTHSTATION_ROUTE_TO_DESTINATION(17151, "No earth station route to terminal"),
    ERR_INVALID_RESEND_COUNT(17152, "Invalid num of times to send a message"),
    ERR_INVALID_REPEAT_INTERVAL(17153, "Invalid interval of repeating a message"),
    ERR_INVALID_VAIN(17154, "Invalid VAIN"),
    ERR_MESSAGE_CONTENT_IS_INVALID(17408, "Invalid message content - general"),
    ERR_PRIORITY_INVALID(17413, "Invalid message priority"),
    ERR_NOT_AUTHORIZED_TO_USE_THE_PRIORITY(17414, "Not authorized to use this message priority"),
    ERR_INVALID_BEAM(17415, "Invalid beam number"),
    ERR_PROCESSING_ERROR(17664, "General processing error"),
    ERR_DUPLICATE_LOGIN(17667, "Duplicate login"),
    ERR_INVALID_USER_OR_PASSWORD(17668, "Invalid user name or password"),
    ERR_VAR_TRAFFIC_OFF_BY_OP(17670, "VAR forward traffic turned off by operator"),
    ERR_VAR_LOGIN_IN_PROGRESS(17674, "VAR login in progress"),
    ERR_FIRST_VAR_MESSAGE_NOT_LOGIN(17675, "The first message received from VAR is not the VAR login message"),
    ERR_VAR_DISCONNECTED_BY_OP(17676, "The VAR connection is disconnected by operator"),
    ERR_TOO_MANY_BAD_MESSAGES_RECEIVED(17677, "Too many bad messages received from VAR"),
    ERR_MESSAGE_TOO_LONG(17678, "The message from VAR is too long"),
    ERR_MAX_QUEUED_MESSAGES_FOR_MOBILE_REACHED(17681, "The maximum number of queued messages for a specified mobile was reached"),
    ERR_MAX_ACTIVE_MSGS_REACHED(17682, "The maximum number of active messages reached"),
    ERR_CANCEL_MSG_DOES_NOT_EXIST(17683, "To cancel a message, but the message does not exist"),
    ERR_MAX_OUTSTANDING_MSG_LEN_OF_VC_REACHED(17684, "The maximum outstanding message length of virtual carrier reached"),
    ERR_MESSAGE_CANNOT_BE_CANCELLED_BECAUSE_ALREADY_SENT(17685, "Message cannot be cancelled because it has already been sent to its destination."),
    ERR_MESSAGE_ACK_ERROR_CONNECTION_LOSS(17686, "Message not ACK-ed due to hub connection loss"),
    ERR_AUTHENTICATION_ERROR(21785, "Gateway service authentication error."),
    ERR_INVALID_WS_PARAMETER(21786, "One of web service operation parameters has invalid value."),
    ERR_NDN_FAILED_CURRENT_BEAM_UPDATE(21787, "Non delivery notification reason: failed to update the current beam number."),
    ERR_NDN_TOO_MANY_MESSAGES(21788, "Non delivery notification reason: too many messages"),
    ERR_NDN_INITIAL_SYSTEM_STATE(21789, "Non delivery notification reason: initial system state"),
    ERR_NDN_UNKNOWN_ERROR(21790, "Non delivery notification reason: unknow"),
    ERR_NDN_INVALID_PRIORITY(21791, "Non delivery notification reason: invalid priority"),
    ERR_NDN_TERMINAL_WAS_RESET(21792, "Non delivery notification reason: terminal was reset"),
    ERR_INVALID_BROADCAST_ID_NOT_YOUR_ID(21793, "Invalid broadcast ID or the ID belongs to some other Gateway account"),
    ERR_RESTRICTED_MESSAGE(21794, "Sending of messages with this SIN and MIN pair is restricted."),
    ERR_REGULAR_MESSAGE_SIZE_QUEUE_FULL(21795, "Maximum number of queued regular sized messages for this terminal has been reached."),
    ERR_LARGE_MESSAGE_SIZE_QUEUE_FULL(21796, "Maximum number of queued large messages for this terminal has been reached."),
    ERR_LOW_POWER_MODE_MESSAGE_TOO_LONG(21797, "Low power mode terminal message is too long."),
    ERR_DUPLICATE_STREAM_DESTINATION(21798, ""),
    ERR_NO_MORE_AVAILABLE_STREAM_HANDLES(21799, ""),
    ERR_MAX_STREAM_COUNT_EXCEEDED(21800, ""),
    ERR_STREAM_ALREADY_CLOSED(21801, "Stream has already been closed"),
    ERR_NOT_YOUR_STREAM(21802, "The stream does not belong to your account"),
    ERR_STREAM_CLOSED(21803, "Stream has already been closed"),
    ERR_STREAM_NOT_FOUND(21804, "Stream not found"),
    ERR_UNABLE_TO_CANCEL_STREAMED_MESSAGE(21805, "Messages belonging to a stream cannot be cancelled"),
    ERR_INVALID_STREAM_HANDLE(21806, "Invalid stream handle"),
    ERR_STREAM_CLOSED_NACK(21807, "Stream closed because a message NACK was received"),
    ERR_STREAM_EXPIRED(21808, "Stream closed because it has expired"),
    ERR_NDN_MAX_RETRY_EXHAUSTED(21809, "Maximum retry or overflow of first packet of low-power message exhausted"),
    ERR_NDN_INVALID_MODEM_WI(21810, "Invalid modem wake-up interval"),
    ERR_NDN_FAILED_TO_LOAD(21811, "Failed to load the message from database"),
    ERR_NDN_TIMEOUT_NO_CHANCE_TO_TRANSMIT(21812, "Message timeout - never had chance to be transmitted"),
    ERR_NDN_TIMEOUT_SOME_ATTEMPTS_TO_TRANSMIT(21813, "Message timeout - some attempts made but never received modem ACK, and later no chance to be transmitted"),
    ERR_NDN_TIMEOUT_SOME_ATTEMPTS_TO_TRANSMIT_ACKED(21814, "Message timeout - some successful attempts made (modem ACK received), but later no chance to be transmitted"),
    ERR_NDN_TERMINAL_RESET_NO_CHANCE_TO_TRANSMIT(21815, "Terminal was reset - never had chance to be transmitted"),
    ERR_NDN_TERMINAL_RESET_SOME_ATTEMPTS_TO_TRANSMIT(21816, "Terminal was reset - some attempts made but never received modem ACK"),
    ERR_UNCHANGED_HWM_DETECTED(21817, "Too many same high-water mark GET requests detected"),
    ERR_MTBP_SESSION_CLEARED(21818, "MTBP session cleared while waiting for an ACK"),
    ERR_INVALID_GATEWAY_ID(21819, "Message destination is not provisioned for this gateway."),
    ERR_TERMINAL_NETWORK_PROVISIONING_PROBLEM(21820, "Terminal not provisioned for any network type."),
    ERR_TOO_MANY_SIMULTANEOUS_GET_REQUESTS(21821, "Too many simultaneous GetMessages requests. If you have multiple clients frequently accessing the same account or more than one client accessing IGWS at the same time, you may reach a request threshold. "),
    ERR_EXPIRE_UTC_TOO_LONG(21822, "MessageExpireUTC upper limit exceeded."),
    ERR_SEND_AFTER_UTC_TOO_LONG(21823, "SendAfterUTC upper limit exceeded."),
    ERR_EXPIRE_UTC_INVALID(21824, "MessageExpireUTC is invalid."),
    ERR_INVALID_PARAMETER_SATELLITE_ONLY(21825, "Invalid parameter. Parameter is valid only for satelitte transport."),
    ERR_INVALID_TRANSPORT_PARAMETER(21826, "Invalid transport parameter."),
    ERR_UNABLE_TO_USE_REQUESTED_TRANSPORT(21827, "Unable to use requested transport."),
    ERR_DELAYED_QUEUE_FULL(21828, "Delayed queue for the destination terminal has reached its limit."),
    ERR_ACCOUNT_NOT_AUTHORIZED_TO_SEND_DELAYED_MESSAGES(21829, "Your account is not authorized to send delayed (send on receive) messages."),
    ERR_UNRESPONSIVE_TERMINAL_QUEUE_LEVEL_REACHED(21830, "Maximum number of queued messages for an unresponsive terminal reached"),
    ERR_OMS_INVALID_SCID(21831, "Message Rejected - Invalid SC identifier or SC does not exist"),
    ERR_OMS_SCMUTED_OR_SUSPENDED(21832, "Message Rejected - SC has been suspended or muted"),
    ERR_OMS_InvalidXMLElement(21833, "Message Rejected - Invalid value specified for XML element(xxx)"),
    ERR_OMS_InvalidXMLRequest(21834, "Message Rejected - Missing or invalid XML request"),
    ERR_OMS_InvalidXMLDocument(21835, "Message Rejected - Invalid or malformed XML document"),
    ERR_OMS_UnknownOMSError(21836, "Unknown OMS error"),
    ERR_OMS_FW_INVALID_MESSAGE_CONTENT(21837, "Invalid OMS FW message content"),
    ERR_OMS_FW_INVALID_MESSAGE_TYPE(21838, "Invalid OMS FW message type"),
    ERR_OMS_FW_INVALID_SCALIAS_ALIAS_EMPTY(21839, "Invalid OMS FW message SC alias - SC alias is empty"),
    ERR_OMS_FW_UNKNOWN_FWMESSAGE_ERROR(21840, "Unknown OMS forward message error");

    private final int code;
    private final String desc;

    /**
     * 根据代码获取枚举
     * 
     * @param code 代码
     * @return 枚举
     */
    public static ErrorCode value(int code) {
        for (ErrorCode value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

}
