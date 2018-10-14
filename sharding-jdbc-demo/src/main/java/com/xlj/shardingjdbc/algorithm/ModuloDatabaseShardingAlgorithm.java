package com.xlj.shardingjdbc.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 分库规则：按照cityId分库，
 * 如果cityId mod 2 为0，则落在springboot_0，也即是springboot1
 * 如果cityId mod 2 为1，则落在springboot_1，也即是springboot2
 *
 * @author xlj
 * @since 0.0.1
 */
public final class ModuloDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {

	
    /* (non-Javadoc)
     * 参数availableTargetNames表示参与分片的所有库实例名列表；
     * 参数shardingValue表示分片键的值；
     * 分片的方法：用库实例名的后缀与分片键的值余数做比较，返回判断为true的库实例名
     */
    @Override
    public String doEqualSharding(final Collection<String> availableTargetNames, final ShardingValue<Integer> shardingValue) {
        for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue() % 2 + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> doInSharding(final Collection<String> availableTargetNames, final ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Collection<Integer> values = shardingValue.getValues();
        for (Integer value : values) {
            for (String each : availableTargetNames) {
                if (each.endsWith(value % 2 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames, final ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Integer> range = shardingValue.getValueRange();
        for (Integer value = range.lowerEndpoint(); value <= range.upperEndpoint(); value++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(value % 2 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
