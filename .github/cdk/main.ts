import { Construct } from "constructs";
import { App, Stack } from "cdkactions";
import {
  createGradleLibraryPullRequestWorkflow,
  createGradleLibraryPrepareForReleaseWorkflow,
  createGradleLibraryReleaseWorkflow
} from "@pixeloutlaw/github-cdkactions";

export class MyStack extends Stack {
  constructor(scope: Construct, id: string) {
    super(scope, id);

    // define workflows here
    createGradleLibraryPullRequestWorkflow(this);
    createGradleLibraryPrepareForReleaseWorkflow(this);
    createGradleLibraryReleaseWorkflow(this);
  }
}

const app = new App();
new MyStack(app, 'cdk');
app.synth();
