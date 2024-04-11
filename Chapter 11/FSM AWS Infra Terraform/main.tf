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
  region = "${var.region}"
}

module "s3" {
    source = "./s3"    
    bucket_name = "${var.fsm_bucket_name}"      
}