resource "aws_s3_bucket" "fsm_bucket" {
    bucket = "${var.bucket_name}"
	tags = {
    purpose = "Disaster Recovery"
  }
}

resource "aws_s3_bucket_public_access_block" "fsm_bucket1" {
    block_public_acls       = false
    block_public_policy     = false
	bucket                  = aws_s3_bucket.fsm_bucket.id
    id                      = aws_s3_bucket.fsm_bucket.id
    ignore_public_acls      = false
    restrict_public_buckets = false
}