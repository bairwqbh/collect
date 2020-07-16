package per.cby.collect.orbcomm.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 消息传输类型
 * 
 * @author chenboyang
 * @since 2020年2月21日
 *
 */
@Getter
@RequiredArgsConstructor
public enum TransportType {

    SUCCESS("SAT", 1, "消息是通过卫星网络发送的"),
    NO_DATA("CELLMTWS", 2, "使用MTWS接口通过蜂窝网络发送了消息"),
    NAME_ERROR("CELLMTBP", 3, "使用MTBP接口通过蜂窝网络发送了消息");

    private final String name;
    private final int code;
    private final String desc;

    /**
     * 根据名称获取枚举
     * 
     * @param name 名称
     * @return 枚举
     */
    public static TransportType value(String name) {
        for (TransportType value : values()) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        return null;
    }

    /**
     * 根据代码获取枚举
     * 
     * @param code 代码
     * @return 枚举
     */
    public static TransportType value(int code) {
        for (TransportType value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

}
