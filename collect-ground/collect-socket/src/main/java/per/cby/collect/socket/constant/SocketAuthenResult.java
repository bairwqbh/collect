package per.cby.collect.socket.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 套接字认证结果
 * 
 * @author chenboyang
 * @since 2020年3月24日
 *
 */
@Getter
@RequiredArgsConstructor
public enum SocketAuthenResult {

    SUCCESS(0x00, "认证成功！"),
    FAIL(0x01, "认证失败！"),
    LENGTH_ERROR(0x02, "认证信息长度有误！"),
    NO_ALPHANUMERIC(0x03, "认证信息只能为字母和数字！"),
    NOT_PASS(0x03, "认证未通过！");

    private final int code;
    private final String desc;

}
