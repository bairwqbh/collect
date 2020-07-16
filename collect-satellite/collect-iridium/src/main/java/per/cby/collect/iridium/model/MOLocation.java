package per.cby.collect.iridium.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 上报消息的定位信息
 * 
 * @author chenboyang
 * @since 2020年2月11日
 *
 */
@Data
@Accessors(chain = true)
public class MOLocation {

    /** 纬度 */
    private double latitude;

    /** 经度 */
    private double longitude;

    /** 半径 */
    private long radius;

}
