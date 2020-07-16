package per.cby.collect.satellite.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 卫星通道
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
@Getter
@RequiredArgsConstructor
public enum SatelliteChannel {

    IRIDIUM("iridium", "铱星"),
    ORBCOMM("orbcomm", "海事"),
    BEIDOU("beidou", "北斗"),
    TIANTONG("tiantong", "天通");

    private final String code;
    private final String desc;

    /**
     * 根据代码获取枚举
     * 
     * @param code 代码
     * @return 枚举
     */
    public static SatelliteChannel value(String code) {
        for (SatelliteChannel value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

}
