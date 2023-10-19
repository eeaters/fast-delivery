interface ChannelStatics {
    channel: string,
    completeNum: number,
    cancelNum: number,
    ongoingNum: number;
}

interface DashboardInfo {
    all: ChannelStatics,
    subChannels: ChannelStatics[]
}
