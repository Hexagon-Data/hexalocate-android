module Fastlane
  module Actions
    class S3UpdateAclAction < Action
      def self.run(params)
        version = params[:version]

        update_acl(params, "com/openlocate/openlocate/#{version}/openlocate-#{version}.aar")
        update_acl(params, "com/openlocate/openlocate/#{version}/openlocate-#{version}.aar.sha1")
        update_acl(params, "com/openlocate/openlocate/#{version}/openlocate-#{version}.aar.md5")

        update_acl(params, "com/openlocate/openlocate/#{version}/openlocate-#{version}.pom")
        update_acl(params, "com/openlocate/openlocate/#{version}/openlocate-#{version}.pom.sha1")
        update_acl(params, "com/openlocate/openlocate/#{version}/openlocate-#{version}.pom.md5")

        update_acl(params, "com/openlocate/openlocate/maven-metadata.xml")
        update_acl(params, "com/openlocate/openlocate/maven-metadata.xml.sha1")
        update_acl(params, "com/openlocate/openlocate/maven-metadata.xml.md5")
      end

      def self.update_acl(params, key)
        args_list = {}
        args_list[:bucket] = params[:bucket]
        args_list[:key] = key
        args_list[:copy_source] = "#{params[:bucket]}/#{key}"
        args_list[:acl] = params[:acl]
        args_list[:storage_class] = 'REDUCED_REDUNDANCY'

        s3_region = params[:region]
        s3_access_key = params[:access_key]
        s3_secret_access_key = params[:secret_access_key]

        require 'aws-sdk'

        client = Aws::S3::Client.new(
          access_key_id: s3_access_key,
          secret_access_key: s3_secret_access_key,
          region: s3_region
        )

        client.copy_object(args_list)
      end

      def self.description
        "Updates ACL for Uploaded pom file and jar file"
      end

      def self.authors
        ["Ulhas Mandrawadkar"]
      end

      def self.return_value
        # If your method provides a return value, you can describe here what it does
      end

      def self.details
        # Optional:
        "Updates ACL for uploaded pom file and jar file"
      end

      def self.available_options
        [
          FastlaneCore::ConfigItem.new(key: :region,
                                       env_name: "",
                                       description: "Region for S3",
                                       is_string: true, # true: verifies the input is a string, false: every kind of value
                                       optional: true), # the default value if the user didn't provide one
          FastlaneCore::ConfigItem.new(key: :access_key,
                                       env_name: "S3_ACCESS_KEY", # The name of the environment variable
                                       description: "Access Key for S3", # a short description of this parameter
                                       verify_block: proc do |value|
                                          raise "No Access key for S3UpdateAclAction given, pass using `access_key: 'access_key'`".red unless (value and not value.empty?)
                                       end,
                                       is_string: true),
          FastlaneCore::ConfigItem.new(key: :secret_access_key,
                                       env_name: "S3_SECRET_ACCESS_KEY", # The name of the environment variable
                                       description: "Secret Access for S3", # a short description of this parameter
                                       verify_block: proc do |value|
                                          raise "No Secret Access for S3UpdateAclAction given, pass using `secret_access_key: 'secret_access_key'`".red unless (value and not value.empty?)
                                       end,
                                       is_string: true),
          FastlaneCore::ConfigItem.new(key: :bucket,
                                       env_name: "S3_BUCKET", # The name of the environment variable
                                       description: "Bucket for S3", # a short description of this parameter
                                       verify_block: proc do |value|
                                          raise "No Bucket for S3UpdateAclAction given, pass using `bucket: 'bucket'`".red unless (value and not value.empty?)
                                       end,
                                       is_string: true),
          FastlaneCore::ConfigItem.new(key: :acl,
                                       env_name: "",
                                       description: "Access level for the file",
                                       is_string: true, # true: verifies the input is a string, false: every kind of value
                                       default_value: "public-read"),
          FastlaneCore::ConfigItem.new(key: :version,
                                       env_name: "", # The name of the environment variable
                                       description: "Version for S3", # a short description of this parameter
                                       verify_block: proc do |value|
                                        raise "No version for S3UpdateAclAction given, pass using `version: 'version'`".red unless (value and not value.empty?)
                                       end,
                                       is_string: true)
        ]
      end

      def self.is_supported?(platform)
        true
      end
    end
  end
end
