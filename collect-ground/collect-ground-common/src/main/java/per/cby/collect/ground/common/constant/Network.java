package per.cby.collect.ground.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 网络通道
 * 
 * @author chenboyang
 * @since 2019年12月10日
 *
 */
@Getter
@RequiredArgsConstructor
public enum Network {

    CM_2G("cm_2g", "中国移动2G网络"),
    CM_3G("cm_3g", "中国移动3G网络"),
    CM_4G("cm_4g", "中国移动4G网络"),
    CM_NB("cm_nb", "中国移动NB网络"),
    CM_BB("cm_bb", "中国移动带宽网络"),

    CT_2G("ct_2g", "中国电信2G网络"),
    CT_3G("ct_3g", "中国电信3G网络"),
    CT_4G("ct_4g", "中国电信4G网络"),
    CT_NB("ct_nb", "中国电信NB网络"),
    CT_BB("ct_bb", "中国电信带宽网络"),

    CU_2G("cu_2g", "中国联通2G网络"),
    CU_3G("cu_3g", "中国联通3G网络"),
    CU_4G("cu_4g", "中国联通4G网络"),
    CU_NB("cu_nb", "中国联通NB网络"),
    CU_BB("cu_bb", "中国联通带宽网络");

    private final String code;
    private final String desc;

}
