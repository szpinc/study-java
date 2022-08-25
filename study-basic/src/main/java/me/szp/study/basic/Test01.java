package me.szp.study.basic;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test01 {


    static String text = "cluster_name\tstring\tzookeeper节点的根目录，去掉了/，有需要的读取\n" +
            "sync_type_conf\tnumber\t当前主备配置的同步模式；0：异步；1：强同步；2：同IDC异步，不同IDC强同步\n" +
            "cpu\tstring\tcpu的数目，100：一个核；50：半个核\n" +
            "data_disk\tstring\t数据盘的大小，单位是M\n" +
            "db_version\tstring\t后端数据库的版本\n" +
            "degrade_flag\tnumber\t是否已经退化的功能；0：没有退化；1：已经退化\n" +
            "degrade\tnumber\t是否支持退化；0：不支持退化；1：支持退化\n" +
            "ebackuptime\tstring\t备份的结束时间点\n" +
            "fenceid\tstring\t独享集群的标识\n" +
            "history_ids\tobject[]\t该set的历史名字，扩容会改变set的名字。type，0：扩容出来的set，1：回档出来的set。时间戳是set开始创建的时间。\n" +
            "id\tstring\tset的唯一标识\n" +
            "log_disk\tstring\t日志盘的大小，单位是M\n" +
            "machine\tstring\t主机机器的类型\n" +
            "memory\tstring\t内存的大小\n" +
            "mydumper\tstring\t是否开启逻辑备份；\"0\":没有开启；\"1\":开启\n" +
            "nodeNum\tnumber\t机器的数目；1：单主；2：一主一备\n" +
            "proxy\tobject[]\t网关的ip地址和端口\n" +
            "proxy_version\tstring\t网关的版本\n" +
            "pwd\tstring\ttdsql这个用户的密码\n" +
            "read_only\tstring\t是否只读；0：读写；1：只读\n" +
            "sbackuptime\tstring\t备份的开始时间\n" +
            "specid\tnumber\t兼容之前的购买逻辑，之前是按照specid来购买set\n" +
            "sqlasyn\tnumber\t当前主备之间的模式；0：异步；1：强同步；2：同IDC异步，不同IDC强同步\n" +
            "status\tnumber\tset的状态；0：正常状态；1：隔离状态；2：未初始化状态；-100 是扩容失败, -101 是回档失败, -102 是水平扩容失败,-103 是授权失败, -1:是删除中\n" +
            "sync_type\tnumber\t与sqlasyn参数一样\n" +
            "user\tstring\t用户名，set购买完成之后，会新建一个tdsql用户\n" +
            "xtrabackup\tstring\t是否开启物理备份；0：不开启；1：开启\n" +
            "zk_name\tstring\t配置的集群的名字\n" +
            "master_zone\tstring\t主DB所在的区\n" +
            "slave_zones\tstring\t备DB所有的区，多个区用分号隔开\n" +
            "kpstatus\tnumber\t正在垂直扩容:100, 正在回档:101 , 正在水平扩容 :102， 1：删除隔离中。0：正常\n" +
            "mask_report\tboolean\t是否上报监控，true为上报，false为不上报\n" +
            "conf_master_zone\tstring\t配置中的master zone【迭代13新增】\n" +
            "conf_slave_zones\tstring\t配置中的slave zone【迭代13新增】\n" +
            "sync_type_conf\tnumber\t当前主备配置的同步模式；0：异步；1：强同步；2：同IDC异步，不同IDC强同步\n" +
            "err_code\tnumber\t错误码。0：成功，非0：具体的错误\n" +
            "err_msg\tstring\t错误信息\n" +
            "tag_exist\tboolean\t实例的保护属性是否存在。true：存在，false：不存在【yun14新增】\n" +
            "tag\tstring\ttag_exist为true时，具体的tag信息【yun14新增】\n" +
            "real_machine\tobject[]\t实例各节点机器对应机型的数组【yun14.4新增】\n" +
            "ip\tstring\treal_machine成员，实例节点ip【yun14.4新增】\n" +
            "machine\tstring\treal_machine成员，实例节点ip对应的机型【yun14.4新增】\n" +
            "db\tobject[]\t数组对象是实例中db机器的状态信息【14.37新增】\n" +
            "ip\tstring\tdb数组的成员，机器ip【14.37新增】\n" +
            "master\tnumber\tdb数组的成员，表示此db节点是否为master。0：非master，1：master【14.37新增】\n" +
            "port\tnumber\tdb数组的成员，表示此db节点的port【14.37新增】\n" +
            "alive\tnumber\tdb数组的成员，表示db节点的存活状态。-1：不存活，0：存活【14.37新增】\n" +
            "readonly\tnumber\tdb数组的成员，表示db节点是否只读。1：只读，0：非只读。无此字段表示没有获取到。【14.37新增】\n" +
            "delay\tnumber\tdb数组的成员，表示此db节点和主节点之间的延迟，单位：秒。无此字段表示没有获取到。【14.37新增】\n" +
            "ipv6_flag\tnumber\t1：支持ipv6；0：不支持ipv6\n" +
            "ipv6\tstring\tipv6的地址，若不支持则为空串\n" +
            "advanced_backup\tobject\t高级备份的配置，比普通备份优先级更高的配置，可以在一周内设置多个备份点\n" +
            "dcnstopatflag\tstring\t在dcn任务中，“1”表示达到stopat设置时间点，“0”表示未达到\n" +
            "coldback_idc\tstring[]\t冷备指定的idc列表\n";

    public static void main(String[] args) {

        Set<String> fields = new HashSet<>();

        Set<String> re = new HashSet<>();

        for (String str : text.split("\n")) {
            String[] split = str.split("\t");
            String field = split[0];
            String type = split[1];
            String comment = split[2];

            if (fields.contains(field)) {
                re.add(field);
            }
            fields.add(field);

            String sqlType = "";

            switch (type) {
                case "number":
                    sqlType = "bigint(20)";
                    break;
                case "boolean":
                    sqlType = "bit(1)";
                    break;
                default:
                    sqlType = "varchar(200)";
                    break;
            }

            String sql = "ALTER TABLE t_instance_tdsql ADD COLUMN " + field + " " + sqlType + " comment '" + comment + "';";
            System.out.println(sql);
        }

        System.out.println("========================");

        re.forEach(System.out::println);
    }
}
