module Fastlane
  module Helper
    class S3UpdateAclHelper
      # class methods that you define here become available in your action
      # as `Helper::S3UpdateAclHelper.your_method`
      #
      def self.show_message
        UI.message("Hello from the s3_update_acl plugin helper!")
      end
    end
  end
end
