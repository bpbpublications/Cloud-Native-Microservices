terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.23"
    }
  }

  required_version = ">= 1.2.0"  
}

provider "aws" {
  region = "us-east-1"
}

resource "aws_s3_bucket" "fsm_bucket_primary" {
}

resource "aws_s3_bucket_public_access_block" "fsm_bucket_primary" {
}