declare module '@capacitor/core' {
  interface PluginRegistry {
    SmsInboxReader: SmsInboxReaderPlugin;
  }
}

export interface SmsInboxReaderPlugin {
  getCount(): Promise<{ count: number }>;
  getAllMessages(): Promise<{ sms: { from: string, message: string }[], count: number }>;
}
