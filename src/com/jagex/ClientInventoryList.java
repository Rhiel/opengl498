package com.jagex;

/**
 * Created by Chris on 5/2/2017.
 */
public class ClientInventoryList {

    public static HashTable clientInvList = new HashTable(32);;

    public static ClientInventory get(int id, boolean bool) {
        long hash = (bool ? 0x80000000 : 0) | id;
        return (ClientInventory) clientInvList.get(hash);
    }

    public static int get_free_slots(int inv_id, boolean bool) {
        if (bool)
            return 0;
        ClientInventory inv = get(inv_id, bool);
        if (inv == null)
            return InvType.getInvType(inv_id).size;
        int free_slots = 0;
        for (int ptr = 0; ptr < inv.slot_obj_id.length; ptr++) {
            if (inv.slot_obj_id[ptr] == -1)
                free_slots++;
        }
        free_slots += InvType.getInvType(inv_id).size - inv.slot_obj_id.length;
        return free_slots;
    }

    public static int get_total_param(int inv_id, boolean bool, int param_id, boolean use_stack_size) {
        ClientInventory inv = get(inv_id, bool);
        if (inv == null)
            return 0;
        int accumulator = 0;
        for (int ptr = 0; ptr < inv.slot_obj_id.length; ptr++) {
            if (inv.slot_obj_id[ptr] >= 0 && inv.slot_obj_id[ptr] < ObjType.itemDefinitionSize) {
                ObjType objtype = ObjTypeList.list(inv.slot_obj_id[ptr]);
                int value = objtype.getParam(param_id, ParamType.getParamType(param_id).defaultint);
                if (use_stack_size)
                    accumulator += inv.slot_obj_count[ptr] * value;
                else
                    accumulator += value;
            }
        }
        return accumulator;
    }

    static final void set_inv_slot(int index, int amount, int itemId, int type, int i_28_) {
        ClientInventory inv = (ClientInventory) clientInvList.get(type);
        if (inv == null) {
            inv = new ClientInventory();
            clientInvList.put(type, inv);
        }
        if (index >= inv.slot_obj_id.length) {
            int[] is = new int[1 + index];
            int[] is_30_ = new int[index + 1];
            for (int i_31_ = 0; inv.slot_obj_id.length > i_31_; i_31_++) {
                is[i_31_] = inv.slot_obj_id[i_31_];
                is_30_[i_31_] = inv.slot_obj_count[i_31_];
            }
            for (int i_32_ = inv.slot_obj_id.length; i_32_  < index; i_32_++) {
                is[i_32_] = -1;
                is_30_[i_32_] = 0;
            }
            inv.slot_obj_id = is;
            inv.slot_obj_count = is_30_;
        }
        inv.slot_obj_id[index] = itemId;
        inv.slot_obj_count[index] = amount;
    }

    static final void clear_inv(int inv_id, int i_0_) {
        if (i_0_ != 1) {
            GrandExchangeOffer.method1444(-101);
        }
        ClientInventory class23_sub17 = (ClientInventory) clientInvList.get(inv_id);
        if (class23_sub17 != null) {
            for (int i_1_ = 0; class23_sub17.slot_obj_id.length > i_1_; i_1_++) {
                class23_sub17.slot_obj_id[i_1_] = -1;
                class23_sub17.slot_obj_count[i_1_] = 0;
            }
        }
    }

    static final int get_item_count(byte b, int i, int i_3_) {
        ClientInventory class23_sub17 = (ClientInventory) clientInvList.get(i);
        if (class23_sub17 == null) {
            return 0;
        }
        if (i_3_ == -1) {
            return 0;
        }
        int i_4_ = 0;
        for (int i_6_ = 0; (i_6_ ^ 0xffffffff) > (class23_sub17.slot_obj_count.length ^ 0xffffffff); i_6_++) {
            if (class23_sub17.slot_obj_id[i_6_] == i_3_) {
                i_4_ += class23_sub17.slot_obj_count[i_6_];
            }
        }
        return i_4_;
    }

    static final int get_slot_obj(int i, int i_26_, int i_27_) {
        ClientInventory class23_sub17 = (ClientInventory) clientInvList.get(i_27_);
        if (class23_sub17 == null) {
            return -1;
        }
        if (i < 0 || (class23_sub17.slot_obj_id.length ^ 0xffffffff) >= (i ^ 0xffffffff)) {
            return -1;
        }
        return class23_sub17.slot_obj_id[i];
    }

    static final int get_slot_total(byte b, int i, int i_0_) {
        if (b != -18) {
            ComponentCanvas.aClass16_49 = null;
        }
        ClientInventory class23_sub17 = (ClientInventory) clientInvList.get(i_0_);
        if (class23_sub17 == null) {
            return 0;
        }
        if ((i ^ 0xffffffff) > -1 || class23_sub17.slot_obj_count.length <= i) {
            return 0;
        }
        return class23_sub17.slot_obj_count[i];
    }

    static final void delete(boolean bool, int i) {
        ClientInventory class23_sub17 = (ClientInventory) clientInvList.get(i);
        if (class23_sub17 != null) {
            class23_sub17.unlink();
        }
    }

    public static final void clearList(int i) {
        clientInvList = new HashTable(i);
    }
}
