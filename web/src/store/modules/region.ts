import {defineStore} from "pinia";
import {region} from "@/api/common";

const region_store = defineStore('region_store', {
    state: (): Cascade[] => {
        return {
            cascades: [],
        }
    },
    actions: {
        async getRegion() {
            if (this.cascades == null || this.cascades.length == 0) {
                this.cascades = await region();
            }
            return this.cascades;
        },
    }
});

export default region_store;
