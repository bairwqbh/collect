package per.cby.collect.common.util;

import org.apache.commons.lang3.ArrayUtils;

import per.cby.collect.common.constant.CollectConstant;
import per.cby.frame.common.util.CodeUtil;

import lombok.experimental.UtilityClass;

/**
 * 采集网关帮助类
 * 
 * @author chenboyang
 * @since 2020年3月20日
 *
 */
@UtilityClass
public class CollectUtil implements CollectConstant {

    /**
     * 是否为FOTA报文
     * 
     * @param msg 报文
     * @return 结果
     */
    public boolean isFotaMsg(byte[] msg) {
        if (ArrayUtils.isEmpty(msg)) {
            return false;
        }
        int length = msg.length;
        if (msg.length <= 4) {
            return false;
        }
        int head = CodeUtil.bytesToInt(new byte[] { msg[0], msg[1] });
        int tail = CodeUtil.bytesToInt(new byte[] { msg[length - 2], msg[length - 1] });
        return head == FOTA_HEAD && tail == FOTA_TAIL;
    }

}
