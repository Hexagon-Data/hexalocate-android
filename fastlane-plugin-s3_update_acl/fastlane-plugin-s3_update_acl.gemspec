# coding: utf-8

lib = File.expand_path("../lib", __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require 'fastlane/plugin/s3_update_acl/version'

Gem::Specification.new do |spec|
  spec.name          = 'fastlane-plugin-s3_update_acl'
  spec.version       = Fastlane::S3UpdateAcl::VERSION
  spec.author        = 'Ulhas Mandrawadkar'
  spec.email         = 'ulhas.sm@gmail.com'

  spec.summary       = 'Updates ACL for Uploaded pom file and jar file'
  # spec.homepage      = "https://github.com/<GITHUB_USERNAME>/fastlane-plugin-s3_update_acl"
  spec.license       = "MIT"

  spec.files         = Dir["lib/**/*"] + %w(README.md LICENSE)
  spec.test_files    = spec.files.grep(%r{^(test|spec|features)/})
  spec.require_paths = ['lib']

  # Don't add a dependency to fastlane or fastlane_re
  # since this would cause a circular dependency

  spec.add_dependency 'aws-sdk', '~> 2.3'

  spec.add_development_dependency 'pry'
  spec.add_development_dependency 'bundler'
  spec.add_development_dependency 'rspec'
  spec.add_development_dependency 'rake'
  spec.add_development_dependency 'rubocop'
  spec.add_development_dependency 'simplecov'
  spec.add_development_dependency 'fastlane', '>= 2.51.0'
end
