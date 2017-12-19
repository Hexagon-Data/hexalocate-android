describe Fastlane::Actions::S3UpdateAclAction do
  describe '#run' do
    it 'prints a message' do
      expect(Fastlane::UI).to receive(:message).with("The s3_update_acl plugin is working!")

      Fastlane::Actions::S3UpdateAclAction.run(nil)
    end
  end
end
