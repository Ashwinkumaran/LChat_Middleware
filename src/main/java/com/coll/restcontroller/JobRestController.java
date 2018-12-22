package com.coll.restcontroller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.coll.DAO.JobDAO;
import com.coll.Model.ApplyingJob;
import com.coll.Model.Job;

@RestController
public class JobRestController 
	{	
		@Autowired
		JobDAO jobDAO;
		@PostMapping(value = "/addJob")
		public ResponseEntity<String> addJob(@RequestBody Job job) {
			if (jobDAO.addJob(job)) {
				return new ResponseEntity<String>("Job Added- Success", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Job insert failed", HttpStatus.NOT_FOUND);
			}
		}
		@GetMapping(value = "/showAllJob")
		public ResponseEntity<List<Job>> listJob() {
			List<Job> listJobs = jobDAO.listAllJobs();
			if (listJobs.size() != 0) {
				return new ResponseEntity<List<Job>>(listJobs, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Job>>(listJobs, HttpStatus.NOT_FOUND);
			}
		}
		@PutMapping(value = "/updateJob/{jobid}")
		public ResponseEntity<String> updateJob(@PathVariable("jobid") int jobid, @RequestBody Job job) {
			System.out.println("Updating Job " + jobid);
			Job job1 = jobDAO.getJob(jobid);
			if (job1 == null) {
				System.out.println("Job with jobid " + jobid + " Not Found");
				return new ResponseEntity<String>("Update Job Failue", HttpStatus.NOT_FOUND);
			}

			job1.setJobid(jobid);
			job1.setCompanyname(job.getCompanyname());
			job1.setLastDateToApply(job.getLastDateToApply());
			job1.setJobdesc(job.getJobdesc());
			job1.setDesgination(job.getDesgination());
			job1.setLocation(job.getLocation());
			jobDAO.updateJob(job1);
			return new ResponseEntity<String>("Update Job ", HttpStatus.OK);
		}
		@GetMapping("/deleteJob/{jobid}")
		public ResponseEntity<String> deleteJob(@PathVariable("jobid") int jobid) {
			Job job = jobDAO.getJob(jobid);

			if (jobDAO.deleteJob(job))
				return new ResponseEntity<String>("Job Deleted", HttpStatus.OK);
			else
				return new ResponseEntity<String>("Job Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		@GetMapping(value = "/getJob/{jobid}")
		public ResponseEntity<Job> getJob(@PathVariable("jobid") int jobid) {
			System.out.println("Get Job " + jobid);
			Job job = jobDAO.getJob(jobid);
			if (job == null) {
				System.out.println("Job  failure..");
				return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Job>(job, HttpStatus.OK);
			}
		}
		@PostMapping(value = "/applyJob")
		public ResponseEntity<String> addJob(@RequestBody ApplyingJob applyJob) {
			applyJob.setAppliedDate(new Date());
			if (jobDAO.applyJob(applyJob)) {
				return new ResponseEntity<String>("ApplyJob Added- Success", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Applyjob failed", HttpStatus.NOT_FOUND);
			}
		}
		@GetMapping(value = "/listAppliedJobs/{loginname}")
		public ResponseEntity<List<ApplyingJob>> listAppliedJob(@PathVariable("loginname")String loginname) {
			List<ApplyingJob> listAppliedJobs = jobDAO.getAllAppliedJobDetails(loginname);
			if (listAppliedJobs.size() != 0) {
				return new ResponseEntity<List<ApplyingJob>>(listAppliedJobs, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<ApplyingJob>>(listAppliedJobs, HttpStatus.NOT_FOUND);
			}
		}
	}
