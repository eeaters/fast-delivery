import {defineStore} from "pinia";
import {channel} from "@/api/common";

const channel_store = defineStore('channel_store', {
    state: (): Channel[] => {
        return {
            channels: [],
        }
    },
    actions: {
        async getChannel() {
            if (this.channels == null || this.channels.length == 0) {
                this.channels = await channel();
            }
            return this.channels;
        },
    }
});

export default channel_store;
