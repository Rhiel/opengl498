package com.jagex;/* com.jagex.ns - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public final class HashTableIterator {

    public static int start_callcount;
    public static int next_callcount;
    public int bucket_pos = 0;
    public HashTable table;
    public Linkable      node_pos;

    public final Linkable next() {
        next_callcount++;
        if ( bucket_pos > 0 && node_pos != table.nodes[bucket_pos - 1] ) {
            Linkable node = node_pos;
            node_pos = node.next;
            return node;
        }
        while ( bucket_pos < table.size ) {
            Linkable node = table.nodes[bucket_pos++].next;
            if ( table.nodes[bucket_pos - 1] != node ) {
                node_pos = node.next;
                return node;
            }
        }
        return null;
    }

    public final Linkable start() {
        bucket_pos = 0;
        start_callcount++;
        return next();
    }

    public HashTableIterator() {
        /* empty */
    }

    public HashTableIterator(HashTable var_hashTable) {
        table = var_hashTable;
    }

}
